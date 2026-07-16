package co.edu.unbosque.controller;

import com.formdev.flatlaf.FlatLightLaf;

public class AplMain {

	public static void main(String[] args) {

		FlatLightLaf.setup();
		Controller ds = new Controller();
		ds.iniciarGui();
		ds.iniciar();
	}
	}


