package iPeer.jMURK.monster;

public class Monster {

	public Monster() {
		name = "";
		HP = 100;
		CHP = HP;
		AP = 0;
		CC = 0;
		Weapon = "";
		isDead = false;
	}

	public String name, Weapon;
	public int HP, CHP, AP, CC;
	public boolean isDead;
	
}
