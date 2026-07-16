package co.edu.unbosque.view;

import java.util.Scanner;

public class Consola {

	private Scanner sp;

	public Consola() {

		sp = new Scanner(System.in);
	}

	public int leerNumeroEntero() {
		return sp.nextInt();
	}

	public float leerNumeroFlotante() {
		return sp.nextFloat();
	}

	public double leerNumeroDouble() {
		return sp.nextDouble();
	}

	public long leerNumeroLargo() {
		return sp.nextLong();
	}

	public boolean leerBooleano() {
		String entrada = sp.nextLine(); // lee la linea entera
		if (entrada.toLowerCase().contains("si")) {
			return true;
		} else {
			return false;
		}
	}

	public String leerLinea() {
		return sp.nextLine(); // leer nombre completo
	}

	public String leerPalabra() { // leer la primera palabra
		return sp.next();
	}

	/*
	 * las cadenas de caracteres tienes indices los indices sirven para identificar
	 * a que indice me estoy refiriendo y a la posicion del caracter
	 */

	public void escribirConSalto(String texto) {
		System.out.println(texto);
	}

	public void escribirSinSalto(String texto) {
		System.out.println(texto);
	}

	/*
	 * cuando se esta llelendo un numero entero, flotante, o decimal y lo siguiente
	 * que voy a leer es una cadena de caracteres debo quemar la linea flotante
	 */

	public void quemarLinea() {
		sp.nextLine();

	}

}
