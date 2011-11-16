package iPeer.jMURK;

import iPeer.jMURK.err.Item404;
import iPeer.jMURK.item.Item;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SuppressWarnings("static-access")
public class Engine {

	@SuppressWarnings("unchecked")
	public static String saveName() {
		try {
			return PlayerHandler.plyr.p.get("SaveName").toString();
		}
		catch (NullPointerException e) {
			String n = PlayerHandler.plyr.p.get("Name").toString();
			PlayerHandler.plyr.p.put("SaveName", n);
			return n;
		}
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
		return intValue("AP");
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

	public static int[] getWeaponDam(String weapon) throws Item404 {
		try {
			Item i = (Item)Class.forName("iPeer.jMURK.item."+weapon.replaceAll(" ","")).newInstance();
			int[] dam = {i.minDam, i.maxDam};
			return dam; // 0 = min, 1 = max
		} catch (Exception e) {
			throw new Item404();
		}
	}

	public static void loadMonsterFiles() throws IOException { //@deprecated

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
	
	public static String getTicksAsGameTime(int t) {
		try {
			String m2 = "00";
			int min = 0, sec = 0;
			min = t/60;
			sec = t%60;
			if (Settings.getBooleanSetting("12hrclock") && min > 12)
				m2 = min < 10 ? "0"+Integer.toString(min) : Integer.toString(min - 12);
			else
				m2 = min < 10 ? "0"+Integer.toString(min) : Integer.toString(min);
			String s2 = sec < 10 ? "0"+Integer.toString(sec) : Integer.toString(sec);
			return m2+":"+s2;
		}
		catch (NullPointerException e) {
			return "00:00";
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

	public static File getMostRecentSave(String charname) {
		File f1 = new File("saves/"+charname+"/save.msf");
		File f2 = new File("saves/"+charname+"/autosave.msf");
		if (f1.lastModified() < f2.lastModified())
			return f2;
		return f1;
	}
	
	public static List<String> getPerksAsList() {
		String[] p = Engine.getPlayerPerks().split("\\,");
		List<String> p2 = new ArrayList<String>();
		for (int a = 0; a < p.length; a++) {
			p2.add(p[a]);
		}
		return p2;
	}
	
	public static String getPerksAsString() {
		List<String> p = PlayerHandler.plyr.perks;
		String s = new String();
		for (int a = 0; a < p.size(); a++) {
			s = s+(a > 0 ? "," : "")+p.get(a);
		}
		return s;
	}

	private static String getPlayerPerks() {
		try {
			return PlayerHandler.plyr.p.get("Perks").toString();
		}
		catch (NullPointerException e) {
			return "Perky Perk";
		}
	}

}
