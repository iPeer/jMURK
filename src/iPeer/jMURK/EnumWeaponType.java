package iPeer.jMURK;
;
public enum EnumWeaponType { 
	SOUL("SOUL",2.0), 
	FIRE("FIRE",2.3), 
	WATER("WATER", 1.7), 
	AIR("AIR", 1.3), 
	EARTH("EARTH", 2.1), 
	ELECTRIC("ELECTRIC", 2.5),
	NORMAL("NORMAL", 1.0);

	private EnumWeaponType(String s, double m) {
		multi = m;
		type = s;
	}

	public double getMuliplier() {
		return multi;
	}

	public String getType() {
		return type;
	}
	
	private final double multi;
	private final String type;

}

