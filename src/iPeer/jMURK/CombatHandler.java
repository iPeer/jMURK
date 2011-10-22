package iPeer.jMURK;

import java.io.InputStream;
import java.util.*;

public class CombatHandler {

	public CombatHandler() { }

	public static void combatInit() {
		Random r = new Random();
		int m = 1; // debug
		//int m = r.nextInt(monsters.size()) + 1;
		System.out.println(m);
		Properties monster = new Properties();
		try {
			InputStream is = CombatHandler.class.getClassLoader().getResourceAsStream("iPeer/jMURK/data/monster/"+Integer.toString(m)+".dat");
			monster.load(is);
		}
		catch (Exception e) {
			ErrorHandler.e(3, "Unable to load Monster File");
			e.printStackTrace();
		}
		playerHP = Engine.getPlayerHP();
		playerCHP = Engine.getPlayerCHP();
		playerAP = Engine.getPlayerAP();
		playerCC = Engine.getPlayerCC();
		playerWeapon = Engine.getPlayerWeapon();
		
		monsterHP = Integer.parseInt(monster.get("HP").toString());
		monsterCHP = monsterHP;
		monsterAP = Integer.parseInt(monster.get("AP").toString());
		monsterCC = Integer.parseInt(monster.get("CC").toString());
		monsterMinHit = Integer.parseInt(monster.get("minDam").toString());
		monsterMaxHit = Integer.parseInt(monster.get("maxDam").toString());
		monsterName = monster.getProperty("Name").toString();
		monsterWeapon = monster.get("weapon").toString();
		
		combatTurn = r.nextInt(1) + 1;
		System.out.println(combatTurn);
	}
	public static int playerHP, playerCC, playerAP, playerCHP, monsterHP, monsterCHP, monsterAP, 
	monsterCC, monsterMinHit, monsterMaxHit, combatTurn;
	public static String monsterName, monsterWeapon, playerWeapon;
	public String[] playerArmour = PlayerHandler.getPlayerArmour(); // 0 = head, 1 = body, 2 = legs, 3 = shield
	public static List<String> monsters = new ArrayList<String>();
}
