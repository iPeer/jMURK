package iPeer.jMURK;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ClickHandler {

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void ClickJDialog (String win, ActionEvent e, JDialog d) {
		String cmd = e.getActionCommand();
		if (win == "jMURKTestDialog") {
			if (cmd == "Close Me") { ExitHandler.closeJDialog(d); }
			else if (cmd == "Save Game") {
				plyr = new Player("Test Save");
				System.out.println(Engine.playerHP());
				System.out.println(plyr.p.get("Name"));
				plyr.p.put("CC", Integer.toString(1337));
				plyr.save(0);

			}
			else if (cmd == "Load Game") {
				plyr = new Player("Really?");
				plyr.load(new File("saves/Test Save.msf"));
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
