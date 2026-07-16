package co.edu.unbosque.util.exception;

public class InvalidPhoneNumberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4067866038898390100L;
	
	public InvalidPhoneNumberException() {
		super("El numero Telefono debe tener entre 9 y 13 caracteres con su identificador");
	}

}
