package iPeer.jMURK;

public class ErrorHandler {

	public ErrorHandler () {
	}
	
	public static void e(int code, String text) {
		if (code == 1) {
			System.out.println("FATAL - "+text);
		}
		if (code == 2) {
			System.out.println("SEVERE - "+text);
		}
		if (code == 3) {
			System.out.println("FILE I/O - "+text);
		}
			
	}
}
