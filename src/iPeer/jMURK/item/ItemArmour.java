package iPeer.jMURK.item;

public class ItemArmour extends Item {

	public ItemArmour() {
		name = "";
		hasHP = false;
		val = 0;
		damReduce = 0;
		HP = 0;
		subType = "";
		type = "armour";
	}
	
	public int val, damReduce, HP;
	public String type, subType, name;
	public boolean hasHP;
	
}
