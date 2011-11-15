package iPeer.jMURK;

import java.io.File;

import iPeer.jMURK.err.Item404;

public class Tests {

	public static void main(String[] args) {
		try {
			Debug.p(ItemHandler.getItemData("Test Weapon").hasHP);
			Debug.p(ItemHandler.getItemData("Test Weapon").minDam);
			Debug.p(ItemHandler.getItemData("Test Aid").HP);
			Debug.p(ItemHandler.getItemData("Test Aid").overheals);
			Debug.p(Utils.getFileMD5(new File("F:\\Dropbox\\jMURK\\jMURK\\saves\\iPeer\\save.msf")));
		} catch (Item404 e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
