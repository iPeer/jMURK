package iPeer.jMURK;


import java.util.*;

import iPeer.jMURK.monster.Monster;
@SuppressWarnings({ "unchecked", "static-access" })
public class CombatHandler {
	
	public CombatHandler() { }

	public static void combatInit() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Random r = new Random();
		int i = r.nextInt(MonsterList.length);
		i = -1; // DEBUG
		String m = MonsterList[i + 1];
		System.out.println(m);
		playerHP = Engine.getPlayerHP();
		playerCHP = Engine.getPlayerCHP();
		playerAP = Engine.getPlayerAP();
		playerCC = Engine.getPlayerCC();
		playerWeapon = Engine.getPlayerWeapon();
		playerWeaponDamage = Engine.getWeaponDam(playerWeapon);
		playerLevel = Engine.getPlayerLevel();
		playerCoins = Engine.getPlayerCoins();
		playerEXP = Engine.getPlayerEXP();
		playerDifficultyMulti = Integer.parseInt(Double.toString(Engine.getDifficultyMultiplier(PlayerHandler.getDifficulty()))); // Yo dawg
		monster = (Monster)Class.forName("iPeer.jMURK.monster."+m.replaceAll(" ","")).newInstance();
		monster.level *= (playerLevel + (r.nextInt(10) * playerDifficultyMulti)); // Somewhat randomised monster levels while still being "around" the player's
		monster.exp *= monster.level;
		monsterName = monster.name; // Not needed... Just using it to remind me to make it update the window.
		// TODO: Make this update combat dialog with the monster's name.
		combatTurn = r.nextInt(2) + 1;
		if (combatTurn == 2)
			playerHasAttacked = true;
	}
	
	public static void monsterAttack() {
		//set initial damage with difficulty multiplier
		Random d = new Random();
		int dam = d.nextInt(monster.maxDam) + monster.minDam;
		// Difficulty multiplier
		dam *= playerDifficultyMulti;
		// Monster's critical chance
		if (d.nextInt(100) <= (monsterCC > 90 ? 90 : monsterCC))
			dam *= 2;
		// Modify damage based upon player stats & bonuses.
		dam -= Math.floor(dam * ((playerAP > 90 ? 90 : playerAP) / 100)); // Yay easy way to calc percentage!	
		// Update the players HP.
		playerCHP -= dam;
		// Check if the player is dead.
		if (playerCHP <= 0)
			playerIsDead();
	}
	
	private static void playerIsDead() {
		isPlayerDead = true;
		int playerCoins = Integer.parseInt(PlayerHandler.plyr.p.get("Coins").toString());
		playerCoins -= (monster.exp * playerDifficultyMulti);
		PlayerHandler.plyr.p.put("Coins", Integer.toString(playerCoins));
		int playerLoses = Integer.parseInt(PlayerHandler.plyr.p.get("Loses").toString());
		playerLoses += 1;
		PlayerHandler.plyr.p.put("Loses", Integer.toString(playerLoses));
		PlayerHandler.save(1);
		//TODO: Add dialog related stuff.
	}
	
	public static void playerAttack() {
		Random d = new Random();
		int dam = d.nextInt(playerWeaponDamage[1]) + playerWeaponDamage[0];
		// Apply monster's AP and other bonuses
		if (d.nextInt(100) <= (playerCC > 90 ? 90 : playerCC))
			dam *= 2;
		dam -= Math.floor(dam * ((monsterAP > 90 ? 90 : monsterAP) / 100));	
		// Update the monster's HP
		monster.CHP -= dam;
		// Check if the monster is dead
		if (monster.CHP <= 0)
			monsterIsDead();	
	}
	
	public static void monsterIsDead() {
		monster.isDead = true;
		playerCoins += (monster.exp * playerDifficultyMulti);
		playerEXP += monster.exp;
		int playerWins = Integer.parseInt(PlayerHandler.plyr.p.get("Wins").toString());
		playerWins += 1;
		PlayerHandler.plyr.p.put("Wins", Integer.toString(playerWins));
		PlayerHandler.save(1);
	}
	
	public static int playerHP, playerCC, playerAP, playerCHP, monsterHP, monsterCHP, monsterAP, 
	monsterCC, monsterMinHit, monsterMaxHit, combatTurn, playerLevel, playerDifficultyMulti, playerCoins,
	playerEXP;
	public static int[] playerWeaponDamage;
	public static String monsterName, monsterWeapon, playerWeapon;
	public static boolean playerHasAttacked = false;
	public static boolean isPlayerDead = false;
	public String[] playerArmour = PlayerHandler.getPlayerArmour(); // 0 = head, 1 = body, 2 = legs, 3 = shield
	private static Monster monster;
	public static List<String> monsters = new ArrayList<String>();
	private static String[] MonsterList = {"Test Monster", "Another Test Monster"};
	
}
