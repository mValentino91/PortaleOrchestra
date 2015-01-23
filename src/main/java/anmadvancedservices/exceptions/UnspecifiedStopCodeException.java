package anmadvancedservices.exceptions;

public class UnspecifiedStopCodeException extends Exception {

	public UnspecifiedStopCodeException() {
		super("Attenzione: non e' stato specificato un codice palina");
	}
	
	public UnspecifiedStopCodeException(String message) {
		super(message);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5041950598529414668L;

}
