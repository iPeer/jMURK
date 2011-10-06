package iPeer.jMURK;

import java.awt.event.*;
import javax.swing.*;

public class ClickHandler {
public static void ClickJFrame (String win, ActionEvent e, JFrame f) {
	String cmd = e.getActionCommand();;
	//int mod = e.getModifiers();
	//System.out.println(cmd);
	//System.out.println("ClickHandler: Action "+cmd+" (Mod: "+mod+") from window "+win);
	if (win == "jMURKHub") {
		
		if (cmd == "Test! :)") {
			System.out.println("Testing!");
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
