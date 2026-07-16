package co.edu.unbosque.util.exception;

public class InvalidNITNumberException extends ExceptionInInitializerError{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4387002587504045544L;
	
	public InvalidNITNumberException() {
		super(" El NIT debe ser de 9 numeros obligatoriamente ");
	}

}
