package iPeer.jMURK;

public class Starter {
	
	public Starter() {
	}
	
	public static void main(String[] args) {
		ErrorHandler.l();
		System.out.println("InterfaceHandler started");
		try {
		jMURKHub m = new jMURKHub();
		m.create();
		System.out.println("InterfaceHandler: jMURKHub");
		}
		catch (Exception e) {
			ErrorHandler.e(1,"Unable to start InterfaceHandler" + e);
		}
	}
	
	//public static InterfaceHandler IH;
}

