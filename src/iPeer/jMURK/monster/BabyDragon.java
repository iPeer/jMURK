package iPeer.jMURK.monster;

import iPeer.jMURK.EnumType;

import java.util.Arrays;

public class BabyDragon extends Monster {

	public BabyDragon() {
		name = "Baby Dragon";
		HP = 3;
		level = 1;
		AP = 10;
		CC = 1;
		minDam = 2;
		maxDam = 7;
		maxdrops = 5;
		type = EnumType.DRAGON;
		basehp = 50;
		drops = Arrays.asList("Wooden Sword", "Wooden Shield", "Copper Sword", "Baul");
		dropsnumber = Arrays.asList(2, 1, 1, 3);
	}
	
}
