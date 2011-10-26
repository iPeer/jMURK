package iPeer.jMURK;

public class Startup {
	
	public Startup() {
		
	}
	
	public static void main(String[] args) {
		try {
			double i = 1.0;
			System.out.println(i * 2);
			jMURKStartDialog m = new jMURKStartDialog();
			m.create();
			System.out.println("InterfaceHandler started");
		}
		catch (Exception e) {
			ErrorHandler.e(1,"Unable to start InterfaceHandler: "+e);
			e.printStackTrace();
		}
	}
	
	//public static InterfaceHandler IH;
}

