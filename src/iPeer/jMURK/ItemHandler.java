package iPeer.jMURK;

import iPeer.jMURK.err.Item404;
import iPeer.jMURK.item.Item;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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

	public static String getItemTypeFromDat(String item, Boolean returnSubType) { //@deprecated
		InputStream is = ItemHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+item+".dat");
		Properties i = new Properties();
		try { i.load(is); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to load item file"); }
		String t = i.get("type").toString();
		if (returnSubType) { String st = i.get("subtype").toString(); return st; }
		return t;
	}

	public static Item getItemData(String item) throws Item404 {
		try {
			Item i = (Item)Class.forName("iPeer.jMURK.item."+item.replaceAll(" ","")).newInstance();
			return i;
		}
		catch (Exception e) {
			Debug.p(e);
			EH.e(1, "Unable to load Item information.");
			throw new Item404();
		}
	}
	
	public static boolean doesItemHaveHP(String item) throws Item404 {
		return getItemData(item).hasHP;
	}

	public static Object getOtherItemValue(String item, String value) { // @deprecated
		InputStream is = ItemHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+item+".dat");
		Properties i = new Properties();
		try { i.load(is); }
		catch (Exception e) { ErrorHandler.e(1, "Unable to load item file"); }
		try { return i.get(value); }
		catch (NullPointerException n) { return "unset"; }
	}

	public static String getOtherItemValueAsString(String item, String value) { //@deprecated
		return getOtherItemValue(item, value).toString();
	}
	
	public static String getItemType(String i) throws Item404 {
		try {
			return getItemData(i).type.toLowerCase();
		}
		catch (NullPointerException n) {
			Debug.p("Item doesn't have Type, returning default!");
			return "none";
		}
	}
	
	public static String getItemSubType(String i) throws Item404 {
		try {
			return getItemData(i).subType.toLowerCase();
		}
		catch (NullPointerException n) {
			Debug.p("Item doesn't have subType, returning default!");
			return "none";
		}
	}

	@SuppressWarnings("unused")
	public static void playerUnequipItem(String item) throws Item404 {
		String itemType = getItemTypeFromList(item);
		if (itemType == "weapon") {	
			pl.plyr.p.put("Weapon", "Hands");
		}
		else if (itemType == "armour") {
			//TODO: Finish coding
			Item d = getItemData(item);
			int playerHP = Engine.getPlayerHP(), playerCHP = Engine.getPlayerCHP(), playerAP = Engine.getPlayerAP(), playerCC = Engine.getPlayerCC(); 
			int playerDR = Engine.getPlayerDR();
			int itemDR = (Integer)d.damReduce;
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
