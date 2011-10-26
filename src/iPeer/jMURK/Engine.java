package iPeer.jMURK;

import iPeer.jMURK.item.ItemWeapon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
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
		try {
			ItemWeapon i = (ItemWeapon)Class.forName("iPeer.jMURK.item."+weapon.replaceAll(" ","")).newInstance();
			int[] dam = {i.minDam, i.maxDam};
			return dam; // 0 = min, 1 = max
		} catch (Exception e) {
			ErrorHandler.e(1, "Unable to create class for weapon");
			e.printStackTrace();
		}
		return null; // Hopefully, it never gets here...
	}

	public static void loadMonsterFiles() throws IOException { //Deprecated

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

	public static double getDifficultyMultiplier (int d) {
		switch (d) {
		case 1:
			return 1.0;
		case 2:
			return 1.5;
		case 3:
			return 2.0;
		case 4:
			return 3.0;
		default:
			return 1.0;
		}
	}

	public static int getPlayerCoins() {
		return intValue("Coins");
	}

	public static int getPlayerWins() {
		return intValue("Wins");
	}
	
	public static int getPlayerLoses() {
		return intValue("Loses");
	}

	public static boolean useAutoAttack() {
		try {
			return (intValue("AutoAttack") == 1 && PlayerHandler.getDifficulty() < 2 ? true : false);			
		}
		catch (NullPointerException e) {
			return false;
		}
	}

	public static String getPlayerName() {
		try {
			return PlayerHandler.plyr.p.get("Name").toString();
		}
		catch (NullPointerException e) {
			return "None";
		}
	}
	
	public static String getTimeOfDayFromTicks(int t) {
		if (Utils.isBetween(t,0,719))
			return "Morning";
		else if (Utils.isBetween(t,720,1079))
			return "Afternoon";
		else
			return "Night";
	}

}
