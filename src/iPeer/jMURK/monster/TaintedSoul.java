package iPeer.jMURK.monster;

import iPeer.jMURK.EnumType;

import java.util.Arrays;

public class TaintedSoul extends Monster {

	public TaintedSoul() {
		name = "Tainted Soul";
		HP = 2;
		level = 2;
		AP = 1;
		CC = 1;
		minDam = 3;
		maxDam = 9;
		maxdrops = 50;
		drops = Arrays.asList("Wooden Sword", "Wooden Shield", "Leather Tunic", "Leather Cap", "Leather Slacks", "Soul Sword", "Soul Essence");
		dropsnumber = Arrays.asList(2, 2, 3, 3, 3, 1, 5);
		type = EnumType.GHOST;
		basehp = 70;
	}
	
}
