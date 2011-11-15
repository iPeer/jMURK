package iPeer.jMURK.item;

public class ItemAid extends Item {

	public ItemAid() {
		
		name = "";
		HP = 0;
		type = "Aid";
		val = 0;
		overheals = false;
		
	}
	
	public int HP, val;
	public String name, type;
	public boolean overheals;
	
}
