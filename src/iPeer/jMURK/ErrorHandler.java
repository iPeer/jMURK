package iPeer.jMURK;

public class ErrorHandler {

	public ErrorHandler () {
	}
	
	public static void l() {
		System.out.println("ErrorHandler loaded");
	}
	
	public static void e(int code, String text) {
		//Depreciated 
		if (code == 0) {
			System.out.println(s+" Hello! If you're seeing this, then the error handler loaded successfully! "+text);
		}
		if (code == 1) {
			System.out.println(s+" FATAL - "+text);
		}
		if (code == 2) {
			System.out.println(s+" SEVERE - "+text);
		}
			
	}
	public static String s = "ErrorHandler:";
}
