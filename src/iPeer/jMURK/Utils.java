package iPeer.jMURK;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;

@SuppressWarnings("rawtypes")
public class Utils {

	public Utils() { }

	public static Dimension resolution() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		return tk.getScreenSize();
	}

	public static String fixTrailingInventoryCommas(String str) {
		System.out.println(str);
		char start = str.charAt(0);
		boolean startIsComma = start == ',' ? true : false;
		char end = str.charAt(str.length() - 1);
		boolean endIsComma = end == ',' ? true : false;
		System.out.println(start+"/"+startIsComma+"/"+end+"/"+endIsComma);
		if (startIsComma) {
			System.out.println("Start of inventory is a comma, fixing");
			str = str.substring(1,str.length());
		}
		if (endIsComma) {
			System.out.println("End of inventory is a comma, fixing");
			str = str.substring(0, str.length() - 1);
		}
		// Final cleanup
		String s2 = str.replaceAll(",,", ",");
		return s2;
	}

	public static void fixFont(Font fnt) {
		FontUIResource f = new FontUIResource(fnt);
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				//FontUIResource orig = (FontUIResource) value;
				Font font = new Font(f.getFontName(), f.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}

	}

}
