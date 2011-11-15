package iPeer.jMURK.item;

public class ItemPendant extends Item {

	public ItemPendant() {
		
		name = "";
		hasHP = false;
		val = 0;
		HP = 0;
		type = "Pendant";
		damReduce = 0;
		HPboost = 0;
		
	}
	
	public String name, type;
	public boolean hasHP;
	public int val, HP, damReduce, HPboost;
	
}
