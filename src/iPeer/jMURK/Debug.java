package iPeer.jMURK;

public class Debug {

	public static void println (int i) {
		System.out.println(i);
	}
	
	public static void println (long l) {
		System.out.println(l);
	}
	
	public static void println (String s) {
		System.out.println(s);
	}
	
	public static void println (boolean b) {
		System.out.println(b);
	}
	
	public static void println (char c) {
		System.out.println(c);
	}
	
	public static void println (Exception e) {
		System.out.println(e);
	}

	public static void p(String s) {
		println(s);		
	}

	public static void p(Exception e) {
		println(e);
	}

	public static void p(int i) {
		println(i);
	}

	public static void p(String[] s) {
		for (int a = 0;a<s.length;a++) {
			println("[D] "+s[a]);
		}
	}

	public static void p(boolean b) {
		println(b);
	}
	
	
}
