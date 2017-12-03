package uk.com.tradecompany.exception;

public class InvalidTradingValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidTradingValueException () {
    }

    public InvalidTradingValueException (String message) {
        super (message);
    }

}
