package br.com.profsalu.model;

import javax.swing.JLabel;

public class Calculadora {

	private Double memoria = 0.;
	private Double resultado = 0.;

	public void somar(JLabel lblVisor) {
		resultado = memoria + Double.parseDouble(lblVisor.getText());
		memoria = resultado;
	}

	public void subtrair(JLabel lblVisor) {
		resultado = memoria - Double.parseDouble(lblVisor.getText());
		memoria = resultado;
	}

	public void multiplicar(JLabel lblVisor) {
		resultado = memoria * Double.parseDouble(lblVisor.getText());
		memoria = resultado;		
	}

	public void dividir(JLabel lblVisor) {
		if (Double.parseDouble(lblVisor.getText()) != 0) {
			resultado = memoria / Double.parseDouble(lblVisor.getText());
			memoria = resultado;
		}else{
			resultado = 0.;
			memoria = resultado;			
		}
	}
	
	public void salvarNaMemoria(JLabel lblVisor) {
		this.memoria = Double.parseDouble(lblVisor.getText());
		lblVisor.setText("");
	}

	public String getResultado() {
		return resultado.toString();
	}
}
