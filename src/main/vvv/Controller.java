package vvv;

/** Az MVC architektúra Controllerért valósítja meg. */
public class Controller {
	/** A játék példányát tárolja. */
	private final Game game;

	/** A főablak referenciája. */
	private final MainFrame mainFrame;

	/** A várakozás megvalósításához szükséges igaz-hamis érték. */
	private boolean waitingForSkip;

	private TakeActionBox takeActionBox;

	private MoveBox moveBox;

	private CraftBox craftBox;

	private ApplyBox applyBox;

	/** Kontroller, ami létrehozza a vezérlő ablakokat. */
	public Controller(Game game) {
		mainFrame = new MainFrame(game, this, this::createPlayer, this::startGame);
		this.game = game;
		mainFrame.setVisible(true);

		takeActionBox = new TakeActionBox(mainFrame, this::moveEvent, this::craftEvent, this::applyEvent, this::hitEvent,
				this::stealEvent);

		new SkipBox(mainFrame, this::skipCallback);

		new WinBox(mainFrame, this::winAccepted);
	}

	/** A várakozáshoz rendelt változót lehet vele állítani. */
	public void setWaitingForSkip(boolean waitingForSkip) {
		this.waitingForSkip = waitingForSkip;
	}

	/** Callback, amit akkor hív meg a view, ha valaki nyert. */
	public void winAccepted() {
		System.exit(0);
	}

	/** Létrehoz egy játékost. */
	public void createPlayer(String name) {
		Player p = new Player(name);
		game.addPlayer(p);
	}

	/** Callback, ami a játékot indítja. */
	public void startGame() {
		game.start();
	}

	/** Callback, ami a lépést irányítja át a game példánynak. */
	public void moveCallback(Field field) {
		game.getCurrentPlayer().move(field);
		if (!waitingForSkip) {
			game.nextPlayer();
		}
	}

	/** MoveBox-ot hoz létre, ami a lépés kiválasztásához kell. */
	public void moveEvent() {
		moveBox = new MoveBox(mainFrame, this::moveCallback, game);
		moveBox.setVisible(true);
	}

	/** Ágens készítéséhez Callback, a view hívja. */
	public void craftCallback(GeneticCode geneticCode) {
		game.getCurrentPlayer().createAgent(geneticCode);
		game.nextPlayer();
	}

	/** CraftBox-ot hoz létre, ami az ágens készítéshez kell. */
	public void craftEvent() {
		craftBox = new CraftBox(mainFrame, this::craftCallback, game);
		craftBox.setVisible(true);
	}

	/** Felkenéshez Callback, a view hívja. */
	public void applyCallback(Agent agent, Player player) {
		game.getCurrentPlayer().applyAgent(agent, player);
		game.nextPlayer();
	}

	/** ApplyBox-ot hoz lére, ami a felkenéshez kell. */
	public void applyEvent() {
		applyBox = new ApplyBox(mainFrame, this::applyCallback, game);
		applyBox.setVisible(true);
	}

	/** Lebaltázáshoz Callback, a view hívja. */
	public void hitCallback(Player player) {
		game.getCurrentPlayer().kill(player);
		game.nextPlayer();
	}

	/** AttackBox-ot hoz létre, ami a lebaltázáshoz kell. */
	public void hitEvent() {
		AttackBox attackBox = new AttackBox(mainFrame, this::hitCallback, game);
		attackBox.setVisible(true);
	}

	/** Anyag lopásához Callback, a view hívja. */
	public void stealMaterialCallback(Player player) {
		game.getCurrentPlayer().stealMaterial(player);
		game.nextPlayer();
	}

	/** Felszerelés lopásához Callback, a view hívja. */
	public void stealEquipmentCallback(Player player) {
		game.getCurrentPlayer().stealEquipment(player);
		if (!waitingForSkip) {
			game.nextPlayer();
		}
	}

	/** StealBox-ot hoz létre, ami a lopáshoz kell. */
	public void stealEvent() {
		StealBox stealBox = new StealBox(mainFrame, this::stealMaterialCallback, this::stealEquipmentCallback, game);
		stealBox.setVisible(true);
	}

	/** Körből való kimaradáshoz Callback, a view hívja. */
	public void skipCallback() {
		game.nextPlayer();
	}

	public TakeActionBox getTakeActionBox() {
		return takeActionBox;
	}

	public MoveBox getMoveBox() {
		return moveBox;
	}

	public CraftBox getCraftBox() {
		return craftBox;
	}

	public ApplyBox getApplyBox() {
		return applyBox;
	}
}
