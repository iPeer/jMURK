package iPeer.jMURK.item;

public class ItemWeapon {

	public ItemWeapon() {
		name = "";
		minDam = 0;
		maxDam = 0;
		hasHP = false;
		itemHP = 0;
		val = 0;
		type = "Weapon";
	}
	
	public String name, type;
	public boolean hasHP;
	public int minDam, maxDam, itemHP, val;
	
}
