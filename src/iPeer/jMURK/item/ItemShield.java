package iPeer.jMURK.item;

public class ItemShield {

	public ItemShield() {

		name = "";
		hasHP = false;
		HP = 0;
		HPboost = 0;
		damReduce = 0;
		type = "shield";
		effect = "";
		effamount = 0;
		val = 0;
		
	}
	
	public String name, type, effect;
	public boolean hasHP;
	public int val, effamount, damReduce, HP, HPboost;

}
