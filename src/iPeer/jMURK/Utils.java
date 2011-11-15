package iPeer.jMURK;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.MessageDigest;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;

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

	public static void listSaves(DefaultMutableTreeNode n, File f) {
		if (!f.isDirectory()) {
			DefaultMutableTreeNode c = new DefaultMutableTreeNode(f.getName());
			n.add(c);
		}
		else {
			DefaultMutableTreeNode c = n;
			if (!f.getName().equals("saves")) {
				c = new DefaultMutableTreeNode(f.getName());
				n.add(c);
			}
			FilenameFilter fl = new FilenameFilter() {
				public boolean accept(File file, String name) { return !file.getName().equals("hash"); }
			};

			File f2[] = f.listFiles(fl);
			for (int i = 0; i < f2.length; i++) {
				if (!f2[i].getName().equals("hash")) listSaves(c, f2[i]);
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
	
	public static String getFileMD5(File f) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
		FileInputStream i = new FileInputStream(f);
		
		byte[] db = new byte[1024];
		
		for (int n = 0; (n = i.read(db)) != -1;) {
			m.update(db, 0, n);
		}
		
		byte[] m2 = m.digest();
		
		StringBuffer s = new StringBuffer();
		for (int i2 = 0; i2 < m2.length; i2++) {
			s.append(Integer.toString((m2[i2] & 0xff) + 0x100, 16).substring(1));
		}
		
		return s.toString();
	}

	public static String getPathToJar() {
		try { String ptj = Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(); return ptj.substring(1, ptj.length()); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to get path for this jar file."); }
		return null; 
	}

	public static boolean isBetween(int i, int j, int k) {
		if (i >= j && i <= k)
			return true;
		return false;
	}

}
