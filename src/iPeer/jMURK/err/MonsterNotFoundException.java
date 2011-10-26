package iPeer.jMURK.err;

public class MonsterNotFoundException extends Exception {

	public MonsterNotFoundException() {
	}

	public MonsterNotFoundException(String message) {
		super(message);
	}

	public MonsterNotFoundException(Throwable cause) {
		super(cause);
	}

	public MonsterNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
