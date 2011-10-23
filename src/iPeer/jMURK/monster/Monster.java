package iPeer.jMURK.monster;

public class Monster {

	public Monster() {
		name = "";
		HP = 100;
		CHP = HP;
		AP = 0;
		CC = 0;
		weapon = "";
		isDead = false;
		minDam = 0;
		maxDam = 0;
	}
	
	public void updateIsDead() {
		if (HP <= 0)
			isDead = true;
	}

	public String name, weapon;
	public int HP, CHP, AP, CC, minDam, maxDam;
	public boolean isDead;
	
}
