package iPeer.jMURK;

public class ErrorHandler {

	public ErrorHandler () {
	}
	
	public static void e(int code, String text) {
		Debug.println(e(code)+text);		
	}
	public static void e(int c, Exception e) {
		e(c, e.getMessage());
	}
	
	public static String e (int i) {
		switch (i) {
		case 1:
			return "FATAL: ";
		case 2:
			return "SEVERE: ";
		case 3:
			return "FILE I/O: ";
		case 4:
			return "WARNING: ";
		default:
			return "Unknown: ";
		}
	}
}
