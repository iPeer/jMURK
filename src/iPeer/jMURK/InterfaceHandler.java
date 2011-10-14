package iPeer.jMURK;

import javax.swing.*;

public class InterfaceHandler {
	
	public  InterfaceHandler() { }
	// Depreciated
	public static void jMURKHub() {
		jMURKHub m = new jMURKHub();
		m.create();
		//jMURKHub.create();
	}
	public static void jMURKTestDialog(JFrame parent) {
		jMURKTestDialog td = new jMURKTestDialog(parent);
		td.create();
		
	}

}

