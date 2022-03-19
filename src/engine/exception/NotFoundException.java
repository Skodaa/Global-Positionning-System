package engine.exception;

public class NotFoundException extends Exception{
	public NotFoundException() {
		super("Station not found");
	}
}

