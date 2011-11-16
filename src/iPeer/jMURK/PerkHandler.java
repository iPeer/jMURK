package iPeer.jMURK;

import iPeer.jMURK.Perks.Perk;
import iPeer.jMURK.err.NoSuchPerkException;

public class PerkHandler {
	
	public PerkHandler() { }
	
	public static boolean hasPerk(String perk) {
		return PlayerHandler.plyr.perks.contains(perk);
	}
	
	public static void addPerk(String perk) {
		if (!hasPerk(perk))
			PlayerHandler.plyr.perks.add(perk);
	}
	
	public static void removePerk(String perk) {
		PlayerHandler.plyr.perks.remove(perk);
	}
	
	public static Perk getPerkData(String perk) throws NoSuchPerkException {
		try {
			Perk p = (Perk)Class.forName("iPeer.jMURK.Perks."+perk.replaceAll(" ", "")).newInstance();
			return p;
		}
		catch (Exception e) {
			throw new NoSuchPerkException();
		}
		
	}

}
