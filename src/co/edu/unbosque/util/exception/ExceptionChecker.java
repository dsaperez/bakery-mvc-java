package co.edu.unbosque.util.exception;

public class ExceptionChecker {

	public static void verificarNumeroNegativo(int numero) throws NegativeNumberException {
		if (numero < 0) {
			throw new NegativeNumberException();
		}
	}

	public static void verificarNumeroNegativo(long numero) throws NegativeNumberException {
		if (numero < 0) {
			throw new NegativeNumberException();
		}
	}

	public static void verificarNumeroNegativo(double numero) throws NegativeNumberException {
		if (numero < 0) {
			throw new NegativeNumberException();
		}
	}

	public static void verificarNumeroNegativo(float numero) throws NegativeNumberException {
		if (numero < 0) {
			throw new NegativeNumberException();
		}
	}

	public static void verificarCedula(Object numero) throws NegativeNumberException {
		if (numero instanceof Integer) {
			verificarNumeroNegativo((int) numero);
		} else if (numero instanceof Float) {
			verificarNumeroNegativo((float) numero);
		} else if (numero instanceof Double) {
			verificarNumeroNegativo((double) numero);
		} else if (numero instanceof Long) {
			verificarNumeroNegativo((long) numero);
		}
	}

	public static void verificarFormatoTelefono(String telefono) throws InvalidPhoneNumberException {
		if (telefono == null || telefono.length() < 7 || telefono.length() > 13) {
			throw new InvalidPhoneNumberException();
		}
	}

	public static void verificarCampoVacio(String texto) throws EmptyFieldException {
		if (texto == null || texto.trim().isEmpty()) {
			throw new EmptyFieldException();
		}
	}

	public static void verificarEdadMaxima(int numero) throws MaxValueExceededException {
		if (numero > 60) {
			throw new MaxValueExceededException();
		}
	}

	public static void verificarEdadMaxima(float numero) throws MaxValueExceededException {
		if (numero > 60) {
			throw new MaxValueExceededException();
		}
	}
	
	public static void verificarNIT(int numero) throws InvalidNITNumberException {
		 String nitTexto = String.valueOf(numero);
		    if (nitTexto.length() != 9) {
		        throw new InvalidNITNumberException();
		}
	}
	
	public static void verificarSoloLetras(String texto) throws EmptyFieldException, InvalidNameException {
	    if (texto == null || texto.trim().isEmpty()) {
	        throw new EmptyFieldException();
	    }
	    if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
	        throw new InvalidNameException();
	    }
	}
}
