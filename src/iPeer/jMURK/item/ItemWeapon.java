package iPeer.jMURK.item;

import iPeer.jMURK.EnumType;

public class ItemWeapon extends Item {

	public ItemWeapon() {
		name = "";
		minDam = 0;
		maxDam = 0;
		hasHP = false;
		itemHP = 0;
		val = 0;
		type = "Weapon";
		repairCost = 0;
		weaponType = EnumType.NORMAL;
	}
	
}
