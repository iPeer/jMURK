package iPeer.jMURK;

import java.io.IOException;

public class VersionThread {

	public VersionThread() {}

	public void start() {
		Debug.p("Attempting to start update thread.");
		isRunning = true;
		Thread t = new Thread(tick);
		t.run();
	}

	Runnable tick = new Runnable() {
		public void run() {
			while (Engine.isGameLoaded()) {
				try {
					tick();
					Thread.sleep(500000L);
				} catch (InterruptedException e) {
					EH.e(4, "Unable to complete Version check");
				}
			}
		}
	};

	private void tick() {
		try {
			jMURKHub.m.UpdateStatus.setText("Checking...");
			double v = Version.checkVersion();
			String s = "No updates available";
			if (v > Version.version)
				s = "Update Available!";
			jMURKHub.m.UpdateStatus.setText(s);
		} catch (IOException e) {
			jMURKHub.m.UpdateStatus.setText("Can't check for update");
		}	
	}

	public static VersionThread vt = new VersionThread();
	public static boolean isRunning = false;
}
