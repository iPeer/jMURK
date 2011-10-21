package iPeer.jMURK;

public class GameTick {

	public GameTick() {

	}

	public void start() {
		if (Engine.isGameLoaded()) {
			tickTime = Integer.parseInt(Player.p.get("Time").toString());
			Thread tickThread = new Thread(tick);
			tickThread.start();
		}
		else {
			ErrorHandler.e(1, "GameTick: Cannot start tick thread while a no game is loaded.");
			return;
		}
	}

	Runnable tick = new Runnable() {
		public void run() {
			while (Engine.isGameLoaded()) {
				tick();
				try { Thread.sleep(1000L); }
				catch (Exception e) {
					ErrorHandler.e(1, "Unable to complete game tick");
					PlayerHandler.unloadGame();
					return;
				}
			}
		}
	};

	public static void tick() {
		if (tickTime == 1439)
			tickTime = -1;
		tickTime++;
		PlayerHandler.plyr.p.put("Time", Integer.toString(tickTime));
		System.out.println(tickTime);
		jMURKHub.updatejMURKHub(Utils.getTicksAsGameTime(tickTime));

	}

	static int tickTime;


}
