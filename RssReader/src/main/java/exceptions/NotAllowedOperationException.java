package exceptions;

public class NotAllowedOperationException extends Exception{

	private static final long serialVersionUID = 661369463352532122L;

	public NotAllowedOperationException() {
		super();
	}
	
	public NotAllowedOperationException(String message) {
		super(message);
	}

}
