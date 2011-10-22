package iPeer.jMURK;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

	public static int getPlayerAP() {
		return intValue("CC");
	}

	public static int getPlayerDR() {
		return intValue("DR");
	}

	public static String getPlayerInventory() {
		return PlayerHandler.plyr.p.get("Inventory").toString();
	}

	public static boolean isGameLoaded() {
		return !PlayerHandler.plyr.p.isEmpty();
	}

	public static int[] getWeaponDam(String weapon) {
		InputStream is = Engine.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/item/"+weapon+".dat");	
		Properties p = new Properties();
		try { p.load(is); is.close(); }
		catch (Exception e) { ErrorHandler.e(1,"Unable to read resource."); }
		int dam1 = Integer.parseInt(p.get("minDam").toString());
		int dam2 = Integer.parseInt(p.get("maxDam").toString());
		int[] damOut = {dam1, dam2};
		return damOut; // 0 = min, 1 = max
	}

	public static void loadMonsterFiles() throws IOException {

		if (Utils.getPathToJar().endsWith(".jar")) {

			CodeSource s = Engine.class.getProtectionDomain().getCodeSource();

			if(s != null) {
				URL j = s.getLocation();
				ZipInputStream z = new ZipInputStream(j.openStream());
				ZipEntry e = null;

				while((e = z.getNextEntry()) != null ) {
					String en = e.getName();
					if(en.startsWith("iPeer/jMURK/data/monster/") &&  en.endsWith(".dat")) {
						System.out.println(en); 
						CombatHandler.monsters.add(en);
					}
				}
			}
		}
		else {
			// Makes this work in Eclipse as the above returns null.
			System.out.println("Environment is IDE, using secondary routine to retrieve monsters.");
			File d = new File(Utils.getPathToJar()+"/iPeer/jMURK/data/monster/");
			String[] f = d.list();
			for (int i = 0;i < f.length;i++) {
				System.out.println(f[i]);
				CombatHandler.monsters.add(f[i]);
			}
		}

	}

	public static String getPlayerWeapon() {
		return PlayerHandler.plyr.p.get("Weapon").toString();
	}

}
