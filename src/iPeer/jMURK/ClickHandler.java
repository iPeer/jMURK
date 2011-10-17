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
				//PlayerHandler.startNewGame("Testing new Loading and Saving");
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
		String cmd = e.getActionCommand();
		if (win == "jMURKHub") {

			if (cmd == "Test! :)") {		
				InterfaceHandler.jMURKTestDialog(f);
			}
			else if (cmd == "Close") {
				if (JOptionPane.showOptionDialog(f, "Are you sure you want to quit jMURK?", "Confirm Exit", 0, 0, null, null, null) == 0) {
					if (Engine.isGameLoaded()) { PlayerHandler.unloadGame(); }
					System.out.println("Closed!");
					ExitHandler.closeJFrame(f);
					System.exit(0);
				}
			}
			else {
				ErrorHandler.e(2,"ID Not Handled!");
			}

		}
		else if (win == "jMURKStartDialog")
			if (cmd == "Quit jMURK")
				ExitHandler.closeJFrame(f);
				if (Engine.isGameLoaded())
					PlayerHandler.unloadGame(); // DEBUG: The game has no "real" quit button yet, so this acts as it for now.
			else if (cmd == "New Game") {
				String charname = JOptionPane.showInputDialog(f,"Enter your character's name:",null,1);
				if (charname != null) {
					PlayerHandler.startNewGame(charname);
					GameTick gt = new GameTick();
					gt.start();
				}
				else 
					return;
			}
			else if (cmd == "Load Game") {
				LoadGameDialog l = new LoadGameDialog(f);
				l.create();
			}
			else
				ErrorHandler.e(2, "ID not handled.");
	}
	
	public static PlayerHandler pl = new PlayerHandler();
	
}
