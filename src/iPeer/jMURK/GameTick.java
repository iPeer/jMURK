package iPeer.jMURK;

import javax.swing.JOptionPane;

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
				try { 
					tick(); 
					Thread.sleep(1000L);
				}
				catch (Exception e) {
					ErrorHandler.e(1, "Unable to complete game tick");
					e.printStackTrace();
					PlayerHandler.unloadGame();
					JOptionPane.showMessageDialog(null, "Due to some sort of error, the game is unable to continue. Your current game has been saved and unloaded to prevent data loss.\n\nI would appreciate it if you would report this error to me, when doing so please include the message below:\n\n"+e.getCause()+"\n\njMURK will now return to the main menu.");
					jMURKHub.m.dispose();
					if (CombatHandler.playerIsInCombat)
						CombatHandler.c.dispose();
					jMURKStartDialog m = new jMURKStartDialog();
					m.create();
					return;
				}
			}
		}
	};

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void tick() {
		PlayerHandler.plyr.p.put("Time", Integer.toString(tickTime));
		PlayerHandler.plyr.p.put("Day", Integer.toString(gameDay));
		String t = Engine.getTicksAsGameTime(tickTime);
		String t1 = Engine.getDay(gameDay);
		jMURKHub.updatejMURKHub(t1+", "+t+(Engine.isNight() ? "*" : ""));
		if (tickTime == 1439) {
			tickTime = -1;
			if (gameDay == 7)
				gameDay = 0;
			gameDay++;
		}
		if (!Debug.timeIsLocked)
			tickTime++;
		if (CombatHandler.playerIsInCombat) {
			if (CombatHandler.combatTurn == "o")
				CombatHandler.monsterAttack();
			if (CombatHandler.combatTurn == "p" && Engine.useAutoAttack())
				CombatHandler.playerAttack();
		}
	}

	static int tickTime = 0;
	static int gameDay = 1;


}
