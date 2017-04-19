package br.com.profsalu.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import br.com.profsalu.model.Calculadora;
import br.com.profsalu.model.ETipoOperacoes;

public class CalculadoraGui {

	public JFrame frame;
	Double atual;
	Double memoria;
	ETipoOperacoes operAtual;
	Calculadora calc = new Calculadora();

	public CalculadoraGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(CalculadoraGui.class.getResource("/img/ic_calculadora.png")));
		frame.setBounds(100, 100, 433, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelVisor = new JPanel();
		panelVisor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JPanel panelNumeros = new JPanel();

		JPanel panelOperacoes = new JPanel();

		JPanel panelExecutar = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelVisor, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panelNumeros, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelOperacoes,
										GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelExecutar, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(panelVisor, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelOperacoes, 0, 0, Short.MAX_VALUE)
						.addComponent(panelNumeros, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelExecutar, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelVisor.setLayout(new CardLayout(0, 0));

		// Tela
		JLabel lblVisor = new JLabel("");
		lblVisor.setFont(new Font("Digital-7 Italic", Font.PLAIN, 50));
		lblVisor.setHorizontalAlignment(SwingConstants.RIGHT);
		panelVisor.add(lblVisor, "name_4087617224316");
		panelExecutar.setLayout(new CardLayout(0, 0));

		// Resultado
		JButton button_igual = new JButton("=");
		button_igual.setFocusable(false);
		button_igual.setFont(new Font("Tahoma", Font.PLAIN, 80));
		panelExecutar.add(button_igual, "name_3716926934447");
		button_igual.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				switch (operAtual) {
				case SOMA:
					calc.somar(lblVisor);
					lblVisor.setText(calc.getResultado());
					break;

				case SUBTRACAO:
					calc.subtrair(lblVisor);
					lblVisor.setText(calc.getResultado());
					break;

				case MULTIPLICACAO:
					calc.multiplicar(lblVisor);
					lblVisor.setText(calc.getResultado());
					break;

				case DIVISAO:
					calc.dividir(lblVisor);
					lblVisor.setText(calc.getResultado());
					break;

				default:
					break;
				}

			}
		});

		// Operacoes

		JButton button_somar = new JButton("+");
		button_somar.setFocusable(false);
		button_somar.setFont(new Font("Tahoma", Font.PLAIN, 50));
		button_somar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				calc.salvarNaMemoria(lblVisor);
				operAtual = ETipoOperacoes.SOMA;
			}
		});

		JButton button_subtrair = new JButton("-");
		button_subtrair.setFocusable(false);
		button_subtrair.setFont(new Font("Tahoma", Font.PLAIN, 56));
		button_subtrair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				calc.salvarNaMemoria(lblVisor);
				operAtual = ETipoOperacoes.SUBTRACAO;
			}
		});

		JButton button_dividir = new JButton("\u00F7");
		button_dividir.setFocusable(false);
		button_dividir.setFont(new Font("Tahoma", Font.PLAIN, 50));
		button_dividir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				calc.salvarNaMemoria(lblVisor);
				operAtual = ETipoOperacoes.DIVISAO;
			}
		});

		JButton button_multiplicar = new JButton("X");
		button_multiplicar.setFocusable(false);
		button_multiplicar.setFont(new Font("Tahoma", Font.PLAIN, 50));
		button_multiplicar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				calc.salvarNaMemoria(lblVisor);
				operAtual = ETipoOperacoes.MULTIPLICACAO;
			}
		});

		GroupLayout gl_panelOperacoes = new GroupLayout(panelOperacoes);
		gl_panelOperacoes.setHorizontalGroup(gl_panelOperacoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOperacoes.createSequentialGroup().addContainerGap().addGroup(gl_panelOperacoes
						.createParallelGroup(Alignment.LEADING)
						.addComponent(button_subtrair, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_somar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_dividir, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(button_multiplicar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100,
								GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_panelOperacoes.setVerticalGroup(gl_panelOperacoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOperacoes.createSequentialGroup().addGap(29)
						.addComponent(button_dividir, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_multiplicar, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_subtrair, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_somar, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));
		panelOperacoes.setLayout(gl_panelOperacoes);

		// Numeros

		JButton button_0 = new JButton("0");
		button_0.setFocusable(false);
		button_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("0"));
			}
		});
		button_0.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_1 = new JButton("1");
		button_1.setFocusable(false);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				lblVisor.setText(lblVisor.getText().concat("1"));
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_2 = new JButton("2");
		button_2.setFocusable(false);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("2"));
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_3 = new JButton("3");
		button_3.setFocusable(false);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("3"));
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_4 = new JButton("4");
		button_4.setFocusable(false);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("4"));
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_5 = new JButton("5");
		button_5.setFocusable(false);
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("5"));
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_6 = new JButton("6");
		button_6.setFocusable(false);
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("6"));
			}
		});
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_7 = new JButton("7");
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_7.setFocusable(false);
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("7"));
			}
		});

		JButton button_8 = new JButton("8");
		button_8.setFocusable(false);
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("8"));
			}
		});

		button_8.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_9 = new JButton("9");
		button_9.setFocusable(false);
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText(lblVisor.getText().concat("9"));
			}
		});
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JButton button_dot = new JButton(".");
		button_dot.setFocusable(false);
		button_dot.setFont(new Font("Tahoma", Font.PLAIN, 50));
		button_dot.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!lblVisor.getText().contains(".")) {
					lblVisor.setText(lblVisor.getText().concat("."));
				}
			}
		});

		JButton button_clear = new JButton("C");
		button_clear.setFocusable(false);
		button_clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblVisor.setText("");
			}
		});
		button_clear.setFont(new Font("Tahoma", Font.PLAIN, 40));

		GroupLayout gl_panelNumeros = new GroupLayout(panelNumeros);
		gl_panelNumeros.setHorizontalGroup(gl_panelNumeros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNumeros.createSequentialGroup().addContainerGap().addGroup(gl_panelNumeros
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelNumeros.createSequentialGroup()
								.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										ComponentPlacement.UNRELATED)
								.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelNumeros.createSequentialGroup()
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelNumeros.createSequentialGroup()
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelNumeros.createSequentialGroup()
								.addComponent(button_clear, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_dot, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(16, Short.MAX_VALUE)));

		gl_panelNumeros.setVerticalGroup(gl_panelNumeros.createParallelGroup(Alignment.LEADING).addGroup(gl_panelNumeros
				.createSequentialGroup().addGap(27)
				.addGroup(gl_panelNumeros.createParallelGroup(Alignment.LEADING)
						.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelNumeros.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelNumeros.createParallelGroup(Alignment.LEADING)
						.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelNumeros.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelNumeros.createParallelGroup(Alignment.LEADING)
						.addComponent(button_dot, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_clear, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(27, Short.MAX_VALUE)));
		panelNumeros.setLayout(gl_panelNumeros);
		frame.getContentPane().setLayout(groupLayout);
	}
}
