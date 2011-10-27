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
				try { tick(); }
				catch (Exception e) { ErrorHandler.e(1, "Unable to complete game tick!"); }
				try { Thread.sleep(1000L); }
				catch (Exception e) {
					ErrorHandler.e(1, "Unable to complete game tick");
					PlayerHandler.unloadGame();
					return;
				}
			}
		}
	};

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void tick() {
		if (tickTime == 1439)
			tickTime = -1;
		tickTime++;
		if (CombatHandler.playerIsInCombat) {
			System.out.println(CombatHandler.playerIsInCombat + " / "+CombatHandler.combatTurn);
			if (CombatHandler.combatTurn == "o")
				CombatHandler.monsterAttack();
			if (CombatHandler.combatTurn == "p" && Engine.useAutoAttack())
				CombatHandler.playerAttack();
		}
			
		PlayerHandler.plyr.p.put("Time", Integer.toString(tickTime));
		//System.out.println(tickTime);
		jMURKHub.updatejMURKHub(Engine.getTimeOfDayFromTicks(tickTime)+", "+Engine.getTicksAsGameTime(tickTime));

	}

	static int tickTime = 0;


}
