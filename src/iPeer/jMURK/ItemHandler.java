package iPeer.jMURK;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import iPeer.jMURK.item.*;

@SuppressWarnings( {"unchecked", "static-access"} )
public class ItemHandler {

	public ItemHandler() { 
	}

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

	public static Properties getItemDataFromDat(String item) {
		InputStream is = ItemHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+item+".dat");
		Properties i = new Properties();
		try { i.load(is); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to load item file"); }
		return i;
	}
	
	public static boolean doesItemHaveHP(String item) {
		InputStream is = ItemHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+item+".dat");
		Properties i = new Properties();
		try {
			i.load(is);
			@SuppressWarnings("unused")
			int ihp = Integer.parseInt(i.get("itemHP").toString());
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
		catch (IOException e) {
			ErrorHandler.e(3,"Unable to load Item information");
		}
		return false;
	}

	public static Object getOtherItemValue(String item, String value) {
		InputStream is = ItemHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+item+".dat");
		Properties i = new Properties();
		try { i.load(is); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to load item file"); }
		try { return i.get(value); }
		catch (NullPointerException n) { return "unset"; }
	}

	public static String getOtherItemValueAsString(String item, String value) {
		return getOtherItemValue(item, value).toString();
	}

	public static void playerUnequipItem(String item) {
		String itemType = getItemTypeFromList(item);
		if (itemType == "weapon") {	
			pl.plyr.p.put("Weapon", "Hands");
		}
		else if (itemType == "armour") {
			//TODO: Finish coding
			Properties d = new Properties();
			d = getItemDataFromDat(item);
			int playerHP = Engine.getPlayerHP(), playerCHP = Engine.getPlayerCHP(), playerAP = Engine.getPlayerAP(), playerCC = Engine.getPlayerCC(); 
			int playerDR = Engine.getPlayerDR();
			int itemDR = (Integer)d.get("damReduce");
			if (itemDR > 0)
				playerDR -= itemDR;
		}
	}

	public static List<String> Armours = Arrays.asList("Test Armour", "Leather Tunic", "Leather Slacks", "Leather Cap");
	public static List<String> Weapons = Arrays.asList("Test Weapon", "Wooden Sword");
	public static List<String> Aid = Arrays.asList("Test Aid", "Baul");
	public static List<String> Pendants = Arrays.asList("Test Pendant", "Golden Pendant");
	public static List<String> Shields = Arrays.asList("Test Shield", "Wooden Shield");
	public static List<String> Misc = Arrays.asList("Test Misc");
	private static PlayerHandler pl;
}
