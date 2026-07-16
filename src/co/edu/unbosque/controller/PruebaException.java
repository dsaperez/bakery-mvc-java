package co.edu.unbosque.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.unbosque.util.exception.ExceptionChecker;
import co.edu.unbosque.util.exception.NegativeNumberException;

public class PruebaException {
	
	static Scanner lector = new Scanner(System.in);
	
	public static void main(String[] args) {
		long numero;
		long numero2;
		long resultado;
		try {
			numero = lector.nextInt();
			ExceptionChecker.verificarCedula(numero);
			numero2 = lector.nextInt();
			ExceptionChecker.verificarCedula(numero2);
			resultado = numero/numero2;
			System.out.println(resultado);
		} catch (InputMismatchException e) {
			System.out.println("El formato del numero no es valido");		
		} catch (ArithmeticException e) {
			System.out.println("El numero 2 no puede ser cero");
		} catch (NegativeNumberException e) {
			System.out.println("No se aceptan numeros negativos");
		}finally {
			System.out.println("llegue al finally");
		}
	}

}
