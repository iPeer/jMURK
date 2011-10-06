package iPeer.jMURK;

import java.awt.event.*;
import javax.swing.*;

public class ClickHandler {
	
public static void ClickJDialog (String win,ActionEvent e, JDialog d) {
	String cmd = e.getActionCommand();
	if (win == "jMURKTestDialog") {
		if (cmd == "Close Me") { ExitHandler.closeJDialog(d); }
	}
}
	
public static void ClickJFrame (String win, ActionEvent e, JFrame f) {
	String cmd = e.getActionCommand();;
	//int mod = e.getModifiers();
	//System.out.println(cmd);
	//System.out.println("ClickHandler: Action "+cmd+" (Mod: "+mod+") from window "+win);
	if (win == "jMURKHub") {
		
		if (cmd == "Test! :)") {
			InterfaceHandler I = new InterfaceHandler();		
			I.jMURKTestDialog(f);
		}
		else if (cmd == "Close") {
			ExitHandler.closeJFrame(f);
		}
		else {
			ErrorHandler.e(2,"ID Not Handled!");
		}
		
	}
}
}
