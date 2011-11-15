package iPeer.jMURK;

import iPeer.jMURK.err.Item404;

public class Tests {

	public static void main(String[] args) {
		try {
			Debug.p(ItemHandler.getItemData("Test Weapon").hasHP);
			Debug.p(ItemHandler.getItemData("Test Weapon").minDam);
			Debug.p(ItemHandler.getItemData("Test Aid").HP);
			Debug.p(ItemHandler.getItemData("Test Aid").overheals);
		} catch (Item404 e) {
			e.printStackTrace();
		}
	}

}
