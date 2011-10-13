package iPeer.jMURK;

import java.awt.event.*;
import javax.swing.*;

public class ClickHandler {

	public static void ClickJDialog (String win, ActionEvent e, JDialog d) {
		String cmd = e.getActionCommand();
		if (win == "jMURKTestDialog") {
			if (cmd == "Close Me") { ExitHandler.closeJDialog(d); }
			else if (cmd == "Save Game") {
				plyr.save(0);

			}
			else if (cmd == "New Game") {
				plyr = new Player("Test Save");
				System.out.println(plyr.HP);
				System.out.println(plyr.Name);
				plyr.CC = 1337;
			}
		}
	}

	public static void ClickJFrame (String win, ActionEvent e, JFrame f) {
		String cmd = e.getActionCommand();;
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
	
	public static Player plyr;
	
}
