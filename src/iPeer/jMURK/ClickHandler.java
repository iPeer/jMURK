package iPeer.jMURK;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// TODO: Move these routines to the classes of the respective windows.

public class ClickHandler {

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void ClickJDialog (String win, ActionEvent e, JDialog d) {
		String cmd = e.getActionCommand();
		if (win == "jMURKTestDialog") {
			if (cmd == "Close Me") { d.dispose(); }
			else if (cmd == "Save Game") {
				System.out.println(Engine.getPlayerHP());
				System.out.println(PlayerHandler.plyr.p.get("Name"));
				PlayerHandler.plyr.p.put("CC", Integer.toString(1337));
				pl.save(0);

			}
			else if (cmd == "Load Game") {
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
					f.dispose();
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
					PlayerHandler.unloadGame();
				f.dispose();
				System.exit(0);
			}
			else if (cmd == "New Game") {
				String charname = JOptionPane.showInputDialog(f,"Enter your character's name:",null,1);
				if (charname != null) {
					File f1 = new File("saves/"+charname+"/save.msf");
					File f2 = new File("saves/"+charname+"/autosave.msf");
					Debug.p(f1);
					if (f1.exists() || f2.exists()) {
						if (JOptionPane.showOptionDialog(f, "A save file already exists for that profile name. \nDo you want to load this game?", "File exists", JOptionPane.YES_NO_OPTION, 0, null, null, null) == JOptionPane.YES_OPTION) {
							PlayerHandler PH = new PlayerHandler();
							PH.load(Engine.getMostRecentSave(charname));
							f.dispose();
							jMURKHub.m.setVisible(true);
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
