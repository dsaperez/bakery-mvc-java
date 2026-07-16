package co.edu.unbosque.util.exception;

public class InvalidNameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1068772218570089635L;
	
	public InvalidNameException() {
		super(" Solo se permiten letras, no numero");
	}
	
	

}
