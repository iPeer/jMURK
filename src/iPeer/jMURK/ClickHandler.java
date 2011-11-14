package iPeer.jMURK;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
			if (cmd == "Quit jMURK") {
				if (Engine.isGameLoaded())
					PlayerHandler.unloadGame(); // DEBUG: The game has no "real" quit button yet, so this acts as it for now.
				ExitHandler.closeJFrame(f);
				System.exit(0);
			}
			else if (cmd == "New Game") {
				String charname = JOptionPane.showInputDialog(f,"Enter your character's name:",null,1);
				if (charname != null) {
					File f1 = new File("saves/"+charname+"/save.msf");
					File f2 = new File("saves/"+charname+"/autosave.msf");
					Debug.p(f1);
					/*if ((f1.exists() || f2.exists() && 
							JOptionPane.showOptionDialog(f, "A character with this save name already exists. Do you want to create a new save and overwrite the existing one?", "Save file exists!", JOptionPane.YES_NO_OPTION, 0, null, null, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) 
							|| !f1.exists() && !f2.exists()) {*/
					if (f1.exists() || f2.exists()) {
						if (JOptionPane.showOptionDialog(f, "A save file already exists for that profile name. \nDo you want to load this game?", "File exists", JOptionPane.YES_NO_OPTION, 0, null, null, null) == JOptionPane.YES_OPTION) {
							PlayerHandler PH = new PlayerHandler();
							if (f1.lastModified() < f2.lastModified())
								PH.load(f2);
							else
								PH.load(f1);
							f.dispose();
							InterfaceHandler.jMURKHub();
							JOptionPane.showMessageDialog(f, "Loaded most recent save.");
						}
						return;
					}
					f.dispose();
					PlayerHandler.startNewGame(charname);
					InterfaceHandler.jMURKHub();
					GameTick gt = new GameTick();
					gt.start();
				}
				else 
					return;
			}
			else if (cmd == "Load Game") {
				jMURKLoadGame l = new jMURKLoadGame(f);
				l.setVisible(true);
			}
			else
				ErrorHandler.e(2, "ID not handled.");
	}

	public static PlayerHandler pl = new PlayerHandler();

}
