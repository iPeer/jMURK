package iPeer.jMURK;

//import java.io.*;
import java.util.*;

@SuppressWarnings({ "unchecked", "rawtypes" }) // It's mixed data, so this shuts the compiler up about warnings. Doesn't actually effect the user at all.
public class Player {
	public Player(String n) {
		String inventory = "Baul|5,Wooden Sword|1,Leather Schlacks|1,Leather Tunic|1,Leather Cap|1,Golden Pendant|1";
		p.put("HP", Integer.toString(100));
		p.put("CHP", Integer.toString(100));
		p.put("EXP", Integer.toString(0));
		p.put("LVL", Integer.toString(0));
		p.put("Inventory", inventory);
		p.put("ArmourHead", "Leather Cap");
		p.put("ArmourTorso", "Leather Tunic");
		p.put("ArmourLegs", "Leather Schlacks");
		p.put("Weapon", "Wooden Sword");
		p.put("AP", Integer.toString(0));
		p.put("CC", Integer.toString(0));
		p.put("Wins", Integer.toString(0));
		p.put("Loses", Integer.toString(0));
		p.put("Coins", Integer.toString(0));
		p.put("FoundItems", Integer.toString(0));
		p.put("StealSuccess", Integer.toString(0));
		p.put("StealFail", Integer.toString(0));
		p.put("Name", n);
		p.put("SaveName", n);
	}

	public int HP, AP, CC, Wins, Loses, Coins, StealSuccess, StealFail, FoundItems;
	public String Name, SaveName, tempSaveName;
	public static Hashtable p = new Hashtable();
	public String[] Armours = {"Leather Tunic", "Leather Schlacks", "Leather Cap"};
	public String[] Weapons = {"Wooden Sword"};
	public String[] Aid = {"Baul"};
	public String[] Pendants = {"Golden Pendant"};
	public String[] Shields = { };
	public String[] Misc = { };

}
