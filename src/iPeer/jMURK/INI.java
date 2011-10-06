package iPeer.jMURK;

import java.io.*;
import java.util.*;

public class INI {

	public static void Write(File f, String p1, String p2) {
		System.out.println(f.toString() +"\\"+p1+"\\"+p2);
		Properties r = new Properties();
		try {
			r.load(new FileInputStream(f));
			r.setProperty(p1,p2);
			r.list(System.out);
			r.store(new FileOutputStream(f), null);
		}
		catch (Exception e) {
			ErrorHandler.e(1,"Unable to write to INI! "+e);
		}
	}

	public static String read (File f, String p1) {
		String o = "";
		System.out.println(f.getAbsolutePath());
		Properties r = new Properties();
		try {
			r.load(new FileInputStream(f));
			o = r.getProperty(p1);
		}
		catch (Exception e) {
			ErrorHandler.e(1,"Unable to read INI! "+e);
		}
		return o;
	}
}
