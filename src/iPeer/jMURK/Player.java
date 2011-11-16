package iPeer.jMURK;

import java.util.Hashtable;
import java.util.List;
import java.util.Random;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Player {
	public Player(String n) {
		String inventory = "Test Aid|9999,Test Weapon|1,Baul|5,Wooden Sword|1,Leather Slacks|1,Leather Tunic|1,Leather Cap|1,Gold Pendant|1";
		Random r = new Random();
		int Time = r.nextInt(1439);
		p.put("Time", Integer.toString(Time));
		p.put("HP", Integer.toString(100));
		p.put("CHP", Integer.toString(100));
		p.put("EXP", Integer.toString(60));
		p.put("LVL", Integer.toString(1));
		p.put("Inventory", inventory);
		p.put("Perks", "Perky Perk");
		p.put("armourHead", "Leather Cap");
		p.put("armourTorso", "Leather Tunic");
		p.put("armourLegs", "Leather Slacks");
		p.put("armourShield", "Wooden Shield");
		p.put("Weapon", /*"Wooden Sword"*/"Test Weapon");
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
	public List<String> perks;

}
