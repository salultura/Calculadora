package br.com.profsalu.exec;

import java.awt.EventQueue;

import br.com.profsalu.view.CalculadoraGui;

public class Exec {
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraGui window = new CalculadoraGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
