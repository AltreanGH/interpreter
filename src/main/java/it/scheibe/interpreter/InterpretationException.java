package it.scheibe.interpreter;

public class InterpretationException extends Throwable {
	private String message;
	
	private InterpretationException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}

	public static class ParsingException extends InterpretationException {
		public ParsingException(String message) {
			super(message);
		}
	}
}
