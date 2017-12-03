package uk.com.tradecompany.exception;

public class InvalidCurrencyValueException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCurrencyValueException () {
    }

    public InvalidCurrencyValueException (String message) {
        super (message);
    }

    public InvalidCurrencyValueException (Throwable cause) {
        super (cause);
    }

    public InvalidCurrencyValueException (String message, Throwable cause) {
        super (message, cause);
    }
	
	
}
