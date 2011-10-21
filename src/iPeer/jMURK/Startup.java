package iPeer.jMURK;

import java.io.File;

public class Startup {
	
	public Startup() {
	}
	
	public static void main(String[] args) {
		ErrorHandler.l();
		try {
			jMURKStartDialog m = new jMURKStartDialog();
			m.create();
			//InterfaceHandler.jMURKHub();
			PlayerHandler ph = new PlayerHandler();
			ph.load(new File("saves/iPeer/save.msf"));
			System.out.println("InterfaceHandler started");
		}
		catch (Exception e) {
			ErrorHandler.e(1,"Unable to start InterfaceHandler: "+e);
			e.printStackTrace();
		}
	}
	
	//public static InterfaceHandler IH;
}

