package iPeer.jMURK;

import iPeer.jMURK.err.Item404;
import iPeer.jMURK.item.Item;
import iPeer.jMURK.item.ItemAid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

@SuppressWarnings( {"unchecked", "static-access", "unused"} )
public class PlayerHandler {

	public PlayerHandler() { }

	public static void unloadGame() {
		save(0);
		plyr.p.clear();
	}

	public static void startNewGame(String charname) {
		System.out.println("Starting new game with name "+charname);
		plyr = new Player(charname);
		save(1);
	}

	public static void save (int auto) 
	{
		System.out.println("Player: Attempting to save...");
		if (plyr.p.isEmpty()) {
			ErrorHandler.e(1, "Cannot save while hash is empty!");
			return;
		}
		else {
			tempSaveName = Engine.saveName();	
			File sd = new File("saves/"+tempSaveName);
			if (!sd.exists())
				sd.mkdirs();
			File sf = new File(sd,auto == 1 ? "autosave.msf" : "save.msf");
			System.out.println(sd.getAbsolutePath());
			System.out.println(sf.getAbsolutePath());
			Properties sf2 = new Properties();
			try
			{
				sf2.putAll(plyr.p);
				sf2.store(new FileOutputStream(sf), "jMURK Save Game");
				System.out.println("Player: Save successful.");
			}
			catch (Exception e) {
				ErrorHandler.e(1,"Unable to create save file: "+e);
				e.printStackTrace();
			}
			try { 
				String chksum = Utils.getFileCRC32(sf);
				chk.put(auto == 1 ? "autosave" : "save", chksum);
				File chko = new File(sd, "hash");
				chk.store(new FileOutputStream(chko), "jMURK Save Checksums");
				System.out.println("Save file's CRC32 is: "+chksum);
			}
			catch (Exception e) { ErrorHandler.e(1, "Unable to generate CRC32 for save file."); e.printStackTrace(); }
		}
	}
	public void load (File f) {
		if (plyr.p.isEmpty())
			ErrorHandler.e(2, "Trying to load file into empty hash.");
		System.out.println(f.getAbsolutePath());
		if (f.exists()) {
			try {
				Properties checksum = new Properties();
				checksum.load(new FileInputStream(f.getParent()+"\\hash"));
				String cs = Utils.getFileCRC32(f);
				Debug.p(cs);
				if (!cs.equals(checksum.get(f.getName().replace(".msf", "")))) {
					JOptionPane.showMessageDialog(null, "This save file appears to have been edited. Please don't do that ;)");
					return;
				}
			}
			catch (Exception e) { ErrorHandler.e(1, "Unable to load checksum for specified save file!"); e.printStackTrace(); return; }
			Properties l = new Properties();
			try {
				l.load(new FileInputStream(f));
				plyr.p.putAll(l);
				//l.putAll(plyr.p);
				GameTick.tickTime = Integer.parseInt(plyr.p.get("Time").toString());
				GameTick gt = new GameTick();
				gt.start();
				System.out.println("Player: Loaded save game successfully.");
			}
			catch (Exception e) {
				ErrorHandler.e(1,"Player: Unable to load save file: "+e);
			}
		}


	}

	public static void addEXP(int exp) {
		int XP = Engine.getPlayerEXP();
		int newEXP = exp + XP;
		plyr.p.put("EXP", Integer.toString(newEXP));
		doPlayerLevelUp();		
	}

	public static void doPlayerLevelUp() {
		int ol = Engine.getPlayerLevel();
		Random r = new Random();
		int e = Engine.getPlayerEXP();
		int l = Engine.getPlayerLevel();
		int c = Engine.getPlayerCC();
		int h = Engine.getPlayerHP();
		int ccu = Engine.getPlayerCritIncreaseChance(); // TODO: Actually code this...
		for (; e >= getEXPAtLevel(l + 1); l++) {
			System.out.println("Player is now level "+l);
			if (r.nextInt(100) <= ccu)
				c++;
			h++;
		}
		plyr.p.put("HP", Integer.toString(h));
		plyr.p.put("LVL", Integer.toString(l));
		plyr.p.put("CC", Integer.toString(c));
		save(1);
		if (l > ol)
			JOptionPane.showMessageDialog(CombatHandler.c, "Congratulations! You have advanced to level "+l+"!");
	}

	public static int getEXPAtLevel(int l) {
		int mexp = 0, mx = 0, level = l - 1, txp;
		while (mx <= level) {
			int a = (int)Math.floor(mx + 300 * Math.pow(2, mx / 8));
			txp = (a / 5);
			mexp += txp;
			mx++;
		}
		return mexp;
	}

	public static void completeQuest(int questID) {
		int EXP = Engine.getPlayerEXP();
		// TODO: Finish coding.		
	}

	public static void addItem (String item, int itemQuant) {
		/* 
		 * The following regex works in mSL AND PHP. But not in Java. Fuck this shit, man.
		 * 
		 * /,?(Baul\|[0-9]+),?/
		 * /,?(Baul\|\d+),?/
		 * 
		 * * Extra escapes should be added for Java. Still doesn't work, though.
		 */	
		// Sadly, this has to be re-done for everything that uses it...
		String r = "("+item+"\\|[0-9\\.]+)"; // This works...
		String inv = Engine.getPlayerInventory();
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(inv);
		if (m.find()) {	
			System.out.println("Inventory match: "+m.group(1));
			String[] fullmatch = m.group(1).split("\\|");
			String itemName = fullmatch[0];
			int itemNumber = Integer.parseInt(fullmatch[1]);
			itemNumber += itemQuant;
			String newString = itemName+"|"+itemNumber;
			String newInv = m.replaceFirst(newString);
			plyr.p.put("Inventory", newInv);
		}
		else {
			System.out.println("Adding "+itemQuant+" of '"+item+"' to the player's inventory");
			inv = inv+","+item+"|"+itemQuant;
			plyr.p.put("Inventory", inv);
		}
	}

	public static void removeItem (String item, int itemQuant) {
		// Sadly, this has to be re-done for everything that uses it...
		String r = "("+item+"\\|[0-9\\.]+)"; // This works...
		String inv = Engine.getPlayerInventory();
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(inv);
		if (m.find()) {	
			System.out.println("Inventory match: "+m.group(1));
			String[] fullmatch = m.group(1).split("\\|");
			String itemName = fullmatch[0];
			int itemNumber = Integer.parseInt(fullmatch[1]);
			itemNumber -= itemQuant;
			String newString = itemName+"|"+itemNumber;
			String newInv = m.replaceFirst(itemNumber > 0 ? newString : "");
			newInv = Utils.fixTrailingInventoryCommas(newInv);
			System.out.println("New Inventory: "+newInv);
			plyr.p.put("Inventory", newInv);
		}
	}

	public static void useAid (String aid, int aidQuant) throws Item404 {
		if (playerHasItem(aid, aidQuant)) {
			try {
				Item a = (Item)Class.forName("iPeer.jMURK.item."+aid.replaceAll(" ","")).newInstance();
				int playerHP = Engine.getPlayerHP();
				int playerCHP = Engine.getPlayerCHP();
				Debug.p(a.HP);
				Debug.p(playerCHP);
				playerCHP += a.HP;
				System.out.println("HP after healing: "+playerCHP);
				if (playerCHP > playerHP && !a.overheals)
					playerCHP = playerHP;
				System.out.println("Final HP: "+playerCHP);
				if (CombatHandler.playerIsInCombat)
					CombatHandler.playerCHP = playerCHP;
				plyr.p.put("CHP", Integer.toString(playerCHP));
				removeItem(aid, aidQuant);
			}
			catch (Exception e) {
				throw new Item404();
			}
		}
	}

	public static boolean playerHasItem(String item, int itemQuant) {
		// Sadly, this has to be re-done for everything that uses it...
		String r = "("+item+"\\|[0-9\\.]+)";
		String inv = Engine.getPlayerInventory();
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(inv);
		if (m.find()) {	
			System.out.println("Inventory match: "+m.group(1));
			String[] fullmatch = m.group(1).split("\\|");
			int n = Integer.parseInt(fullmatch[1]);
			if (n >= itemQuant)
				return true;
			else
				return false;

		}
		return false;
	}

	public static String[] getPlayerArmour() {
		String playerArmourHead, playerArmourBody, playerArmourLegs, playerArmourShield;
		playerArmourHead = plyr.p.get("armourHead").toString();
		playerArmourBody = plyr.p.get("armourTorso").toString();
		playerArmourLegs = plyr.p.get("armourLegs").toString();
		playerArmourShield = plyr.p.get("armourShield").toString();
		String[] armour = {playerArmourHead, playerArmourBody, playerArmourLegs, playerArmourShield};
		return armour;
	}

	public static int getDifficulty() {
		/*
		 * 
		 * Difficulties:
		 * 1 = easy
		 * 2 = normal
		 * 3 = hard
		 * 4 = insane
		 * 
		 */
		try {
			return Integer.parseInt(plyr.p.get("Difficulty").toString());
		}
		catch (NullPointerException e) {
			return 1;
		}
	}

	public static void listInventory(jMURKInventoryDialog ID, boolean b, boolean c, boolean d, boolean e, boolean f) {
		System.out.println(ID);
		String[] i = Engine.getPlayerInventory().split("\\,");
		ID.progressBar.setMaximum(i.length);
		ID.progressBar.setMinimum(0);
		ID.progressBar.setValue(0);
		ID.progressBar.setVisible(true);
		DefaultListModel lm = ID.lm;
		JList list = ID.list;
		try {
			for (int x = 0; x < i.length; x++) {
				String i2 = i[x].split("\\|")[0];
				int i3 = Integer.parseInt(i[x].split("\\|")[1]);
				String t = ItemHandler.getItemType(i2);
				if (t.equals("weapon") && b) {
					lm.add(list.getModel().getSize(), i2+" ("+i3+")");
				}
				else if (t.equals("armour") && c) {
					lm.add(list.getModel().getSize(), i2+" ("+i3+")");
				}
				else if (t.equals("aid") && d) {
					lm.add(list.getModel().getSize(), i2+" ("+i3+")");
				}
				else if (t.equals("pendant") && e) {
					lm.add(list.getModel().getSize(), i2+" ("+i3+")");
				}
				else if (t.equals("misc") && f) {
					lm.add(list.getModel().getSize(), i2+" ("+i3+")");
				}
				ID.progressBar.setValue(x + 1);
				if (x + 1 == ID.progressBar.getMaximum())
					ID.progressBar.setVisible(false);
			}
		} 
		catch (Item404 e1) {
			EH.e(1, "Unable to list inventory.");
		}

	}
	
	public static void listInventory(DefaultListModel l) {
		String[] i = Engine.getPlayerInventory().split("\\,");
		for (int a = 0;a < i.length;a++) {
			String i1 = i[a].split("\\|")[0];
			String i2 = i1;
			Debug.p(i[a]);
			int i3 = Integer.parseInt(i[a].split("\\|")[1]);
			l.add(l.getSize(), i2+"("+i3+")");
		}
		
	}

	public static Player plyr;
	public static String tempSaveName;
	private static Properties chk = new Properties();


}
