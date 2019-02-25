package ch.lanza.rbm.domain.exceptions;

public class ConnectionException extends Exception{
	private static final long serialVersionUID = -7180395182280220086L;
	
	public ConnectionException(String message) {
		super(message);
	}
}
