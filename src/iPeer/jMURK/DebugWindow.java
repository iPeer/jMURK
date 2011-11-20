package iPeer.jMURK;

import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JDialog;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class DebugWindow extends JFrame {

	private JTextField textField;
	public JList list;
	DefaultListModel lm = new DefaultListModel();

	public DebugWindow() {
		setTitle("jMURK: Debug Console");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);

		list = new JList(lm);
		getContentPane().add(list, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(list);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {  
			public boolean dispatchKeyEvent(KeyEvent e) {  
				boolean keyHandled = false;  
				if ((e.getID() == KeyEvent.KEY_PRESSED) && (textField.isFocusOwner() || list.isFocusOwner() || getContentPane().isFocusOwner())) {  
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
						keyHandled = true;
						parseCommand(textField.getText());
					}
				}
				return keyHandled;   
			}


		}); 
	}
	@SuppressWarnings({ "unchecked", "static-access" })
	private void parseCommand(String t) {
		String[] t1 = t.split("\\ ");
		String c, p;
		try {
			c = t1[0].toLowerCase();
			p = t1[1];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			c = t1[0].toLowerCase();
			p = "";
		}
		if (c.equals("debugcommandsenabled")) {
			Debug.debugCommandsActive = Boolean.parseBoolean(p);
			Debug.c("DebugCommandsEnabled = "+Debug.debugCommandsActive);
		}
		if (Debug.debugCommandsActive) {
			if (c.equals("setplayerhp")) {
				PlayerHandler.plyr.p.put("HP", p);
				PlayerHandler.plyr.p.put("CHP", p);
				Debug.c("PlayerHP = "+ p);
			}
			else if (c.equals("alwaysplayersturn")) {
				if (p.equals("")) {
					Debug.c(Debug.isAlwaysPlayersTurn);
				}
				else {
					Debug.isAlwaysPlayersTurn = Boolean.parseBoolean(p);
					Debug.c("isAlwaysPlayersTurn = "+Debug.isAlwaysPlayersTurn);
				}
			}
			else if (c.equals("alwaysopponentsturn")) {
				if (p.equals("")) {
					Debug.c(Debug.isAlwaysOpponentsTurn);
				}
				else {
					Debug.isAlwaysOpponentsTurn = Boolean.parseBoolean(p);
					Debug.c("isAlwaysOpponentsTurn = "+Debug.isAlwaysOpponentsTurn);
				}
			}
			else if (c.equals("killopponent")) {
				if (CombatHandler.playerIsInCombat) {
					CombatHandler.monsterIsDead();
				}
				else {
					Debug.c("Player is not in combat.");
				}
			}
			else if (c.equals("killplayer")) {
				if (CombatHandler.playerIsInCombat) {
					CombatHandler.playerIsDead();
				}
				else {
					Debug.c("Player is not in combat.");
				}
			}
			else if (c.equals("playercantakedamage") || c.equals("canplayertakedamage")) {
				if (p.equals("")) {
					Debug.c(Debug.canPlayerTakeDamage);
				}
				else {
					Debug.canPlayerTakeDamage = Boolean.parseBoolean(p);
					Debug.c("canPlayerTakeDamage = "+Debug.canPlayerTakeDamage);
				}
			}
			else if (c.equals("additem")) {
				String[] i = p.split("*.?<=([0-9\\.])");
				Debug.c(i[0]+" -> "+i[1]);
				PlayerHandler.addItem(i[0], Integer.parseInt(i[1]));
			}
			else if (c.equals("removeitem")) {
				String[] i = p.split("*.?<=([0-9\\.])");
				Debug.c(i[0]+" -> "+i[1]);
				PlayerHandler.removeItem(i[0], Integer.parseInt(i[1]));
			}
			else if (c.equals("time")) {
				int ti = Integer.parseInt(p);
				GameTick.tickTime = ti;
				Debug.c("tickTime = "+ti);
			}
			else if (c.equals("locktime")) {
				Debug.timeIsLocked = Boolean.parseBoolean(p);
				String s = Debug.timeIsLocked ? "stopped" : "running";
				Debug.c("Time is now "+s+" ("+GameTick.tickTime+")");
			}
			else if (c.equals("save")) {
				try {
					PlayerHandler.save(1);
				}
				catch (Exception e) {
					Debug.c("Unable to force save");
				}
			}
			else if (c.equals("exit")) {
				dispose();
			}
			else if (c.equals("clear")) {
				lm.clear();
			}
			else if (c.equals("startcombat")) {
				try {
					Debug.c("Starting combat with type "+p);
					CombatHandler.combatInit(Integer.parseInt(p));
				} catch (Exception e) {
					Debug.c("Unable to force init combat");
					e.printStackTrace();
				}
			}
			else if (c.equals("setday")) {
				Debug.c("Set day to "+p+" ("+Engine.getDay(Integer.parseInt(p))+")");
				GameTick.gameDay = Integer.parseInt(p);
			}
			else if (c.equals("setdifficulty")) {
				PlayerHandler.plyr.p.put("Difficulty", p);
				Debug.c("Difficulty = "+p+" ("+Engine.getDifficultyMultiplier(Integer.parseInt(p))+")");
			}
			else {
				Debug.c("Unknown command - \""+c+"\"");
			}
			textField.setText("");
		}
		else {
			Debug.c("Debugging commands are not enabled.");
		}
	}
}
