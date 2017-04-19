package br.com.profsalu.controller;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;

import br.com.profsalu.model.Calculadora;

public class Operacoes {

	private Calculadora calc = new Calculadora();

	public void somar(JLabel lblVisor) {
		calc.setResultado(calc.getMemoria() + Double.parseDouble(lblVisor.getText()));
		calc.setMemoria(calc.getResultado());
	}

	public void subtrair(JLabel lblVisor) {
		calc.setResultado(calc.getMemoria() - Double.parseDouble(lblVisor.getText()));
		calc.setMemoria(calc.getResultado());
	}

	public void multiplicar(JLabel lblVisor) {
		calc.setResultado(calc.getMemoria() * Double.parseDouble(lblVisor.getText()));
		calc.setMemoria(calc.getResultado());
	}

	public void dividir(JLabel lblVisor) {
		if (Double.parseDouble(lblVisor.getText()) != 0) {
			calc.setResultado(calc.getMemoria() / Double.parseDouble(lblVisor.getText()));
			calc.setMemoria(calc.getResultado());
		} else {
			calc.setResultado(0.);
			calc.setMemoria(calc.getResultado());
		}
	}

	public void salvarNaMemoria(JLabel lblVisor) {
		if (!exibirResultado().isEmpty() || exibirResultado() == null) {
			calc.setMemoria(Double.parseDouble(lblVisor.getText()));
			limparTela(lblVisor);
		}
	}

	public void limparTela(JLabel lblVisor) {
		lblVisor.setText("");
	}

	public String exibirResultado() {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);		
		return (nf.format(calc.getResultado()));

	}
}
