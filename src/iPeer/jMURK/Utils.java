package iPeer.jMURK;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.io.Files;

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
				Font font = new Font(f.getFontName(), f.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}

	}

	public static void getFilesAndFolders(DefaultMutableTreeNode n, File f) {
		if (!f.isDirectory()) {
			DefaultMutableTreeNode c = new DefaultMutableTreeNode(f.getName());
			n.add(c);
		}
		else {
			System.out.println(f.getName());
			DefaultMutableTreeNode c = new DefaultMutableTreeNode(f.getName());
			n.add(c);
			FilenameFilter fl = new FilenameFilter() {
				public boolean accept(File file, String name) { return !file.getName().equals("hash"); }
			};

			File f2[] = f.listFiles(fl);
			for (int i = 0; i < f2.length; i++) {
				if (!f2[i].getName().equals("hash")) getFilesAndFolders(c, f2[i]);
			}
		}
	}

	public static File getFirstSubFolderInDirectory(File d) {
		File[] f = d.listFiles();
		for (int i = 0; i < f.length; i++) {
			if (f[i].isDirectory()) { File dir = f[i]; return dir; }
		}
		return d;
	}
	
	public static String getFileCRC32(File f) {
		String c = "";
		try { c = Long.toString(Files.getChecksum(new File(f.getAbsolutePath()), new java.util.zip.CRC32())); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to calculate CRC32."); }
		return c;
	}
	
	public static String getTicksAsGameTime(int t) {
		int min = 0, sec = 0;
		min = t/60;
		sec = t%60;
		String s2 = sec < 10 ? "0"+Integer.toString(sec) : Integer.toString(sec);
		String m2 = min < 10 ? "0"+Integer.toString(min) : Integer.toString(min);
		return m2+":"+s2;
	}

}
