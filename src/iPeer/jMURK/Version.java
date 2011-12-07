package iPeer.jMURK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Version {

	public Version() {}
	
	public static double checkVersion() throws IOException {
		versionurl = new URL("http://dl.dropbox.com/u/21719562/MURK/j/version.txt");
		Debug.p("Checking for update at "+versionurl);
		BufferedReader v = new BufferedReader(new InputStreamReader(versionurl.openStream()));
		String l = v.readLine();
		/*while (l != "EOF") {
			Debug.p(l);
			l = v.readLine();
		}*/
		Debug.p("Version found: "+l);
		return Double.parseDouble(l);
	}
	
	private static URL versionurl;
	public float lv;
	public static double version = 1.0;
	
}


