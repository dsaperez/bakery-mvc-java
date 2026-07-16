package co.edu.unbosque.util.exception;

public class MaxValueExceededException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5454040213081115281L;
	
	public MaxValueExceededException() {
		super("No esta en condiciones de trabajar debido a su edad, mas bien vaya y descanse");
	}

}
