package iPeer.jMURK;

import javax.swing.*;

public class ExitHandler {
	
	public static void closeJFrame (JFrame f) {
		try {
			System.out.println("Closing JFrame \""+f.getName()+"\"");
			f.dispose();
		}
		catch (Exception e) { 
			ErrorHandler.e(1,"Exit Handler is unable to complete the action it was given: "+e);
		}
	}
}
