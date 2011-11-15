package iPeer.jMURK.monster;

import java.util.Arrays;

import iPeer.jMURK.ItemHandler;

public class TestMonster extends Monster {

	public TestMonster() {

		name = "Test Monster";
		HP = 10;
		exp = 100;
		maxDam = 10;
		weapon = "Wooden Sword";
		drops = ItemHandler.Armours;
		dropsnumber = Arrays.asList(10,10,10,10);
		
	}

}
