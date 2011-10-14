package iPeer.jMURK;

@SuppressWarnings("static-access")
public class Engine {

	public static String saveName() {
		return PlayerHandler.plyr.p.get("SaveName").toString();
	}
	
	public static int intValue(String key) {
		return Integer.parseInt(PlayerHandler.plyr.p.get(key).toString());
		
	}

	public static int playerHP() {
		return intValue("HP");
	}
	
	public static int playerCHP() {
		return intValue("CHP");
	}
	
	public double jMURKVERSION = 0.1;
	
}
