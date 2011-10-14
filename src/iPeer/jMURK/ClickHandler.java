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
				PlayerHandler.startNewGame("Testing new Loading and Saving");
				System.out.println(Engine.getPlayerHP());
				System.out.println(PlayerHandler.plyr.p.get("Name"));
				PlayerHandler.plyr.p.put("CC", Integer.toString(1337));
				pl.save(0);

			}
			else if (cmd == "Load Game") {
				//PlayerHandler pl = new PlayerHandler();
				pl.load(new File("saves/Test Save.msf"));
			}
		}
	}

	public static void ClickJFrame (String win, ActionEvent e, JFrame f) {
		String cmd = e.getActionCommand();;
		if (win == "jMURKHub") {

			if (cmd == "Test! :)") {		
				InterfaceHandler.jMURKTestDialog(f);
			}
			else if (cmd == "Close") {
				ExitHandler.closeJFrame(f);
			}
			else {
				ErrorHandler.e(2,"ID Not Handled!");
			}

		}
		else if (win == "jMURKStartDialog")
			if (cmd == "Quit jMURK")
				ExitHandler.closeJFrame(f);
			else
				ErrorHandler.e(2, "ID not handled.");
	}
	
	public static PlayerHandler pl = new PlayerHandler();
	
}
