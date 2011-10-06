package iPeer.jMURK;

import javax.swing.*;

public class InterfaceHandler {
	
	public  InterfaceHandler() { }
	// Depreciated
	public void jMURKHub() {
		jMURKHub m = new jMURKHub();
		m.create();
		//jMURKHub.create();
	}
	public void jMURKTestDialog(JFrame parent) {
		jMURKTestDialog td = new jMURKTestDialog(parent);
		td.create();
		
	}

}

