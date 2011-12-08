package iPeer.jMURK.monster;

import java.util.Arrays;
import java.util.List;

public class Monster {

	public Monster() {
		name = "";
		HP = 1; // Per monster level
		CHP = HP;
		AP = 0; // Per monster level
		CC = 0; // Per monster level
		weapon = "";
		isDead = false;
		minDam = 0;
		basehp = 100;
		maxDam = 0;
		level = 1; // Per player level
		exp = 1; // Per player level
		maxdrops = 1;
		drops = Arrays.asList("Test Weapon", "Test Aid");
		dropsnumber = Arrays.asList(1337, 9001);
		type = "normal";
	}

	public String name, weapon, type;
	public int HP, CHP, AP, CC, minDam, maxDam, level, exp, maxdrops, basehp;
	public boolean isDead;
	public List<String> drops;
	public List<Integer> dropsnumber;
	
}
