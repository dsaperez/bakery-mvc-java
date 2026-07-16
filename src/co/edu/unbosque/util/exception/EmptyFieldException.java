package co.edu.unbosque.util.exception;

public class EmptyFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6585689814006577782L;

	public EmptyFieldException() {
		super("Verifique los campos porque no pueden estar vacios");
	}
}
