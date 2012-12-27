package mx.akii.facebook_messenger;

public class SendException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1609330832579367274L;

	public SendException() {
		super();
	}

	public SendException(String message) {
		super(message);
		
	}

	public SendException(Throwable cause) {
		super(cause);
		
	}

	public SendException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
