package iPeer.jMURK;

public enum EnumType { 
	GHOST("GHOST", 2.0, "DARK"), 
	FIRE("FIRE", 2.3, "WATER"), 
	WATER("WATER", 1.7, "FIRE"), 
	ICE("ICE", 1.3, "FIRE"), 
	EARTH("EARTH", 2.1, "WATER"), 
	ELECTRIC("ELECTRIC", 2.5, "EARTH"),
	NORMAL("NORMAL", 1.0, "EARTH"),
	DARK("DARK", 1.5, "LIGHT"),
	LIGHT("LIGHT", 1.5, "DARK"), 
	DRAGON("DRAGON", 2.8, "DRAGON");

	private EnumType(String t, double m, String o) {
		multi = m;
		type = t;
		opposite = o;
	}

	public double getMuliplier() {
		return multi;
	}

	public String getType() {
		return type;
	}
	
	public String getOpposite() {
		return opposite;
	}
	
	private final double multi;
	private final String type;
	private final String opposite;

}

