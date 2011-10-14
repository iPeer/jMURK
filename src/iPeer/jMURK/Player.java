package iPeer.jMURK;

import java.io.*;
import java.util.*;

@SuppressWarnings({ "unchecked", "rawtypes" }) // It's mixed data, so this shuts the compiler up about warnings. Doesn't actually effect the user at all.
public class Player {
	public Player(String n) {
		String inventory[] = {"Baul|5", "Wooden Sword|1", "Leather Schlacks|1", "Leather Tunic|1", "Leather Cap|1", "Golden Pendant|1"};
		p.put("HP", Integer.toString(100));
		p.put("CHP", Integer.toString(100));
		p.put("EXP", Integer.toString(60));
		p.put("Inventory", inventory);
		p.put("ArmourHead", "Leather Cap");
		p.put("ArmourTorso", "Leather Tunic");
		p.put("ArmourLegs", "Leather Schlacks");
		p.put("Weapon", "Wooden Sword");
		p.put("AP", Integer.toString(0));
		p.put("CC", Integer.toString(0));
		p.put("Wins", Integer.toString(0));
		p.put("Loses", Integer.toString(0));
		p.put("Coins", Integer.toString(100));
		p.put("FoundItems", Integer.toString(0));
		p.put("StealSuccess", Integer.toString(0));
		p.put("StealFail", Integer.toString(0));
		p.put("Name", n);
		p.put("SaveName", n);
	}
	public void save (int auto) 
	{
		System.out.println("Player: Attempting to save...");
		if (p.isEmpty()) {
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
				sf2.putAll(p);
				sf2.store(new FileOutputStream(sf), "jMURK Save Game");
				System.out.println("Player: Save successful.");
			}
			catch (Exception e) {
				ErrorHandler.e(1,"Unable to create save file: "+e);
				e.printStackTrace();
			}
		}
	}
	public void load (File f) {
		if (p.isEmpty())
			ErrorHandler.e(2, "Trying to load file into empty hash.");
		System.out.println(f.getAbsolutePath());
		if (f.exists()) {
			Properties l = new Properties();
			try {
				l.load(new FileInputStream(f));
				p.putAll(l);
				System.out.println(l.get("CC"));
				System.out.println("Player: Loaded save game successfully.");
			}
			catch (Exception e) {
				ErrorHandler.e(1,"Player: Unable to load save file: "+e);
			}
		}


	}

	public int HP, AP, CC, Wins, Loses, Coins, StealSuccess, StealFail, FoundItems;
	public String Name, SaveName, tempSaveName;
	public static Hashtable p = new Hashtable();
	public String Armours[] = {"Leather Tunic", "Leather Schlacks", "Leather Cap"};
	public String Weapons[] = {"Wooden Sword"};
	public String Aid[] = {"Baul"};
	public String Pendants[] = {"Golden Pendant"};
	public String Shields[] = { };
	public String Misc[] = { };

}
