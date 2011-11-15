package iPeer.jMURK.err;

@SuppressWarnings("serial")
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
