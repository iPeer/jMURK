package iPeer.jMURK;

public class Startup {
	
	public Startup() {
	}
	
	public static void main(String[] args) {
		ErrorHandler.l();
		try {
			jMURKStartDialog m = new jMURKStartDialog();
			m.create();
			//InterfaceHandler.jMURKHub();
			System.out.println("InterfaceHandler started");
		}
		catch (Exception e) {
			ErrorHandler.e(1,"Unable to start InterfaceHandler: "+e);
		}
	}
	
	//public static InterfaceHandler IH;
}

