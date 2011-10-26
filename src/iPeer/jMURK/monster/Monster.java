package iPeer.jMURK.monster;

public class Monster {

	public Monster() {
		name = "";
		HP = 1; // Per monster level
		CHP = 0;
		AP = 0; // Per monster level
		CC = 0; // Per monster level
		weapon = "";
		isDead = false;
		minDam = 0;
		maxDam = 0;
		level = 1; // Per player level
		exp = 1; // Per player level
	}
	
	public void updateIsDead() {
		if (HP <= 0)
			isDead = true;
	}

	public String name, weapon;
	public int HP, CHP, AP, CC, minDam, maxDam, level, exp;
	public boolean isDead;
	
}
