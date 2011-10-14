package iPeer.jMURK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.*;

@SuppressWarnings( {"unchecked", "static-access", "unused"} )
public class PlayerHandler {

	public PlayerHandler() { }
	
	public static void startNewGame(String charname) {
		System.out.println("Starting new game with name "+charname);
		plyr = new Player(charname);
		//completeQuest(plyr, 1);
	}
	
	public void save (int auto) 
	{
		System.out.println("Player: Attempting to save...");
		if (plyr.p.isEmpty()) {
			ErrorHandler.e(1, "Cannot save while hash is empty!");
			return;
		}
		else {
			if (auto == 1)
				tempSaveName = "autosave";
			else
				tempSaveName = Engine.saveName();	
			File sd = new File("saves/");
			if (!sd.exists())
				sd.mkdirs();
			File sf = new File(sd,tempSaveName+".msf");
			System.out.println(sd.getAbsolutePath());
			System.out.println(sf.getAbsolutePath());
			Properties sf2 = new Properties();
			try
			{
				sf2.putAll(plyr.p);
				sf2.store(new /*OutputStream*/FileWriter(sf), "jMURK Save Game");
				System.out.println("Player: Save successful.");
			}
			catch (Exception e) {
				ErrorHandler.e(1,"Unable to create save file: "+e);
				e.printStackTrace();
			}
		}
	}
	public void load (File f) {
		if (plyr.p.isEmpty())
			ErrorHandler.e(2, "Trying to load file into empty hash.");
		System.out.println(f.getAbsolutePath());
		if (f.exists()) {
			Properties l = new Properties();
			try {
				l.load(new FileInputStream(f));
				PlayerHandler.plyr.p.putAll(l);
				System.out.println(l.get("Inventory"));
				System.out.println("Player: Loaded save game successfully.");
			}
			catch (Exception e) {
				ErrorHandler.e(1,"Player: Unable to load save file: "+e);
			}
		}


	}
	
	public void addEXP(Player plyr, int exp) {
		int XP = Engine.getPlayerEXP();
		int newEXP = exp + XP;
		plyr.p.put("EXP", Integer.toString(newEXP));
		doPlayerLevelUp();		
	}
	
	public void doPlayerLevelUp() {
		Random r = new Random();
		int e = Engine.getPlayerEXP();
		int l = Engine.getPlayerLevel();
		int c = Engine.getPlayerCC();
		int h = Engine.getPlayerHP();
		int ccu = Engine.getPlayerCritIncreaseChance(); // TODO: Actually code this...
		for (; e >= getEXPAtLevel(l + 1); l++) {
			if (r.nextInt(100)  <= ccu)
				c++;
			l++;
			h++;
		}
		plyr.p.put("HP", Integer.toString(h));
		plyr.p.put("LVL", Integer.toString(l));
		plyr.p.put("CC", Integer.toString(c));
		save(1);
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
	
	public void completeQuest(Player plyr, int questID) {
		int EXP = Engine.getPlayerEXP();
		// TODO: Finish coding quest completion.		
	}
	
	public void addItem (Player plyr, String item, int itemQuant) {
		// TODO: Code item adding.
	}
	
	public void removeItem (Player plyr, String item, int itemQuant) {
		// TODO: Code item removal.
	}
	
	public void useAid (Player plyr, String aid, int aidQuant) {
		// TODO: Code aid usage and removal.
	}
	
	public static boolean playerHasItem(String item, int itemQuant) {
		return false; //TODO: You guessed it! (Code it).
	}
	
	public static Player plyr;
	public String tempSaveName;

	
}
