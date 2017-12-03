package uk.com.tradecompany.exception;

public class InvalidOperationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOperationException () {
    }

    public InvalidOperationException (String message) {
        super (message);
    }

}
