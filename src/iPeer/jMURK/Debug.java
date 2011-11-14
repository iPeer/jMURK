package iPeer.jMURK;

import java.io.File;
import java.lang.reflect.Method;

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

	public static void p(Method[] m) {
		for (int a = 0;a<m.length;a++) {
			println(m[a]);
		}
	}

	private static void println(Method m) {
		System.out.println(m);
	}

	public static void p(File f) {
		System.out.println(f);
	}
	
	
}
