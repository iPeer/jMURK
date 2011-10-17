package iPeer.jMURK;

@SuppressWarnings("static-access")
public class Engine {

	public static String saveName() {
		return PlayerHandler.plyr.p.get("SaveName").toString();
	}
	
	public static int intValue(String key) {
		return Integer.parseInt(PlayerHandler.plyr.p.get(key).toString());
		
	}
	
	public static int getPlayerLevel() {
		return intValue("LVL");
	}
	
	public static int getPlayerCritIncreaseChance() {
		int chance = 25;
		return PlayerHandler.playerHasItem("Critical Up", 1) ? chance*2 : chance;
	}

	public static int getPlayerEXP() {
		return intValue("EXP");
	}
	
	public static int getPlayerCC() {
		return intValue("CC");
	}
	
	public static int getPlayerHP() {
		return intValue("HP");
	}
	
	public static int getPlayerCHP() {
		return intValue("CHP");
	}
	
	public static String getPlayerInventory() {
		return PlayerHandler.plyr.p.get("Inventory").toString();
	}
	
	public static boolean isGameLoaded() {
		return !PlayerHandler.plyr.p.isEmpty();
	}
	
	public double jMURKVERSION = 0.1;

	
}
