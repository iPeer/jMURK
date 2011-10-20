package iPeer.jMURK;

import java.io.*;
import java.util.*;

public class ItemHandler {

	public ItemHandler() { }
	
	public static String getItemTypeFromList(String item) {
		if (Armours.contains(item)) { return "armour"; }
		else if (Weapons.contains(item)) { return "weapon"; }
		else if (Aid.contains(item)) { return "aid"; }
		else if (Pendants.contains(item)) { return "pendant"; }
		else if (Shields.contains(item)) { return "shield"; }
		return "misc";
	}
	
	public static String getItemTypeFromDat(String item, Boolean returnSubType) {
		InputStream is = ItemHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+item+".dat");
		Properties i = new Properties();
		try { i.load(is); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to load item file"); }
		String t = i.get("type").toString();
		if (returnSubType) { String st = i.get("subtype").toString(); return st; }
		return t;
	}
	
	public static List<String> Armours = Arrays.asList("Test Armour", "Leather Tunic", "Leather Slacks", "Leather Cap");
	public static List<String> Weapons = Arrays.asList("Test Weapon", "Wooden Sword");
	public static List<String> Aid = Arrays.asList("Test Aid", "Baul");
	public static List<String> Pendants = Arrays.asList("Test Pendant", "Golden Pendant");
	public static List<String> Shields = Arrays.asList("Test Shield");
	public static List<String> Misc = Arrays.asList("Test Misc");
}
