package iPeer.jMURK;

public class Settings {

	public Settings() {
	}

	public static boolean getBooleanSetting (String s) {
		if (s.equals("12hrclock")) // DEBUG
				return false;
		return true; //TODO: Code.
	}

	public static int getPopUpDisplayDuration() {
		return 6000;
	}

}
