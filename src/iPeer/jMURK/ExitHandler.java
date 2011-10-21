package iPeer.jMURK;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ExitHandler {
	
	public static void closeJDialog (JDialog d) {
		try {
			System.out.println("Closing JDialog \""+d.getName()+"\"");
			d.dispose();
		}
		catch (Exception e) {
			throwerror(1,e);
		}
	}
	
	public static void closeJFrame (JFrame f) {
		try {
			System.out.println("Closing JFrame \""+f.getName()+"\"");
			f.dispose();
		}
		catch (Exception e) {
			throwerror(1,e);
			//ErrorHandler.e(1,"ExitHandler was unable to complete the action it was given: "+e);
		}
	}
	private static void throwerror (int code, Exception e) {
		ErrorHandler.e(code,"ExitHandler was unable to complete the action it was given: "+e);
	}
}
