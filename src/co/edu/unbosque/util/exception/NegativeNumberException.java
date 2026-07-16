package co.edu.unbosque.util.exception;

public class NegativeNumberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2980283458950681246L;

	public NegativeNumberException() {
		super("No se admiten numeros negativos");
	}
}
