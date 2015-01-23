package anmadvancedservices.exceptions;

public class UnspecifiedLineCodeException extends Exception {

	public UnspecifiedLineCodeException() {
		super("Attenzione: non e' stato specificato un codice linea");
	}
	
	public UnspecifiedLineCodeException(String message) {
		super(message);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5041950598529414668L;

}
