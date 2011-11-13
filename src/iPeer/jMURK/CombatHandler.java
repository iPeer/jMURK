package iPeer.jMURK;


import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import iPeer.jMURK.err.Item404;
import iPeer.jMURK.err.MonsterNotFoundException;
import iPeer.jMURK.monster.Monster;

@SuppressWarnings({ "unchecked", "static-access" })
public class CombatHandler {
	
	public CombatHandler() { }

	public static void combatInit(int type) throws MonsterNotFoundException, Item404 {
		c = new jMURKCombat();
		Random r = new Random();
		int i = r.nextInt(type == 1 ? MonsterList.length : BossList.length);
		i = -1; // DEBUG
		String m = type == 1 ? MonsterList[i + 1] : BossList[i + 1];;
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
		//playerDifficultyMulti = Integer.parseInt(Double.toString(Engine.getDifficultyMultiplier(PlayerHandler.getDifficulty()))); // Yo dawg
		playerDifficultyMulti = Engine.getDifficultyMultiplier(PlayerHandler.getDifficulty());
		playerWins = Engine.getPlayerWins();
		playerLoses = Engine.getPlayerLoses();
		try {
			monster = (Monster)Class.forName("iPeer.jMURK.monster."+m.replaceAll(" ","")).newInstance();
		} catch (Exception e) {
			throw new MonsterNotFoundException(e.getMessage());
		}
		monster.level *= Math.floor(playerLevel + (r.nextInt(10) * playerDifficultyMulti)); // Somewhat randomised monster levels while still being "around" the player's
		monster.exp *= monster.level;
		monster.CC *= monster.level;
		monster.AP *= monster.level;
		monster.HP *= monster.level;
		monster.CHP = monster.HP;
		combatTurn = r.nextInt(1) == 1 ? "o" : "p";
		if (combatTurn == "o")
			playerHasAttacked = true;
		playerIsInCombat = true;
		c.Playername.setText(Engine.getPlayerName());
		c.Hp.setText("HP: "+Integer.toString(playerCHP)+"/"+Integer.toString(playerHP));
		c.Ap.setText("AP: "+Integer.toString(playerAP));
		c.Cc.setText("CC: "+Integer.toString(playerCC));
		c.Lvl.setText("LVL: "+Integer.toString(playerLevel));
		
		c.MonsterName.setText(monster.name);
		c.MonsterLVL.setText("LVL: "+Integer.toString(monster.level));
		c.MonsterAP.setText("AP: "+Integer.toString(monster.AP));
		c.MonsterCC.setText("CC: "+Integer.toString(monster.CC));
		c.MonsterHP.setText("HP: "+Integer.toString(monster.CHP)+"/"+Integer.toString(monster.HP));
		
		c.runbutton.setText("Run ("+monster.exp+" coins)");
		c.AutoAttack.setEnabled(PlayerHandler.getDifficulty() < 2 ? true : false);
		
		listAidItems(c.lm, c.list);
		c.setVisible(true);
	}


	public static void monsterAttack() {
		System.out.println("Monster is attacking");
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
		c.Hp.setText("HP: "+Integer.toString(playerCHP < 0 ? 0 : playerCHP)+"/"+Integer.toString(playerHP));
		combatTurn = "p";
		c.Fight.setEnabled(true);
		c.lm2.add(c.list_1.getModel().getSize(), dam > 0 ? "Opponent hits "+dam : "Opponent misses!");
	}
	
	private static void playerIsDead() {
		playerIsInCombat = false;
		isPlayerDead = true;
		playerCoins -= (monster.exp * playerDifficultyMulti);
		PlayerHandler.plyr.p.put("Coins", Integer.toString(playerCoins));
		playerLoses += 1;
		PlayerHandler.plyr.p.put("Loses", Integer.toString(playerLoses));
		PlayerHandler.save(1);
		c.dispose();
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
		combatTurn = "o";
		c.Fight.setEnabled(false);
		c.MonsterHP.setText("HP: "+Integer.toString(monster.CHP < 0 ? 0 : monster.CHP)+"/"+Integer.toString(monster.HP));
		c.lm2.add(c.list_1.getModel().getSize(), dam > 0 ? "Player hits "+dam : "Player misses!");
	}
	
	public static void monsterIsDead() {
		monster.isDead = true;
		playerIsInCombat = false;
		playerCoins += (monster.exp * playerDifficultyMulti);
		PlayerHandler.addEXP(monster.exp);
		playerWins += 1;
		PlayerHandler.plyr.p.put("Wins", Integer.toString(playerWins));
		PlayerHandler.save(1);
		c.dispose();
	}
	
	public static void playerRun() {
		playerIsInCombat = false;
		monster.isDead = true;
		playerCoins -= (monster.exp * playerDifficultyMulti);
		playerLoses += 1;
		PlayerHandler.plyr.p.put("Coins", Integer.toString(playerCoins));
		PlayerHandler.plyr.p.put("Loses", Integer.toString(playerLoses));
		PlayerHandler.save(1);
	}
	
	@SuppressWarnings("rawtypes")
	public static void listAidItems(DefaultListModel lm, JList l) {
		String in = Engine.getPlayerInventory();
		String[] i = in.split(",");
		for (int x = 0; x < i.length; x++) {
			String a = i[x];
			String[] b = a.split("\\|");
			if (ItemHandler.getItemTypeFromList(b[0]) == "aid") {
				String d = b[0]+" ("+b[1]+")";
				int p = l.getModel().getSize();
				lm.add(p, d);
			}
		}
	}
	
	public static int playerHP, playerCC, playerAP, playerCHP, monsterHP, monsterCHP, monsterAP, 
	monsterCC, monsterMinHit, monsterMaxHit, playerLevel, playerCoins,
	playerEXP, playerWins, playerLoses;
	public static double playerDifficultyMulti;
	public static int[] playerWeaponDamage;
	public static String monsterName, monsterWeapon, playerWeapon, combatTurn;
	public static boolean playerHasAttacked = false, isPlayerDead = false, playerIsInCombat = false;
	public String[] playerArmour = PlayerHandler.getPlayerArmour(); // 0 = head, 1 = body, 2 = legs, 3 = shield
	private static Monster monster;
	public static List<String> monsters = new ArrayList<String>();
	private static String[] MonsterList = {"Test Monster"};
	private static String[] BossList = {"Test Boss"};
	public static jMURKCombat c;
}
