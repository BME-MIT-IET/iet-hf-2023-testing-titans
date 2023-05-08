package src.main.vvv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A játék főablakát megvalósító osztály.
 */
public class MainFrame extends JFrame implements ActionListener, ModelObserver {
	/**
	 * A virológust létrehozó callback függvény.
	 */
	private Consumer<String> createFunc;
	/**
	 * A játék indítás callback függvénye.
	 */
	private Runnable startFunc;
	/**
	 * A rajzfelület referenciája, amelyre kirajzolja a játékot.
	 */
	private DrawPanel drawPanel;
	/**
	 * A játék működését megvalósító osztály referenciája.
	 */
	private Game game;
	/**
	 * Az aktuálisan lépő játékos referenciája.
	 */
	private Player currentPlayer;

	/**
	 * A vezérlő referenciája, akihez éppen tartozik az ablak.
	 */
	private Controller controller;

	/**
	 * Létrehozza a főablakot, elmenti a callback függvényeket, továbbá létrehozza a
	 * rajzfelületet.
	 * 
	 * @param game       A játék logikai referenciája.
	 * @param createFunc A virológust létrehozó callback függvény.
	 * @param startFunc  A játékot elindító callback függvény.
	 */
	public MainFrame(Game game, Controller controller, Consumer<String> createFunc, Runnable startFunc) {
		super("Világtalan virológusok világa");
		this.controller = controller;
		this.createFunc = createFunc;
		this.startFunc = startFunc;
		JMenuBar mb = new JMenuBar();
		JMenu newGame = new JMenu("Új játék");
		JMenuItem newGameItem = new JMenuItem("Létrehozása");
		newGame.add(newGameItem);
		newGameItem.addActionListener(this);
		mb.add(newGame);
		this.setJMenuBar(mb);
		drawPanel = new DrawPanel();
		this.add(drawPanel);
		this.game = game;
		ModelPublisher.getModelPublisher().subscribe(this);
		this.setSize(500, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	/**
	 * Újra rajzoltatja a rajzfelület tartalmát a referencia virológus alapján.
	 * 
	 * @param currentPlayer A referencia virológus.
	 */
	public void setPlayer(Player currentPlayer) {
		drawPanel.redraw(currentPlayer, game);
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Létrehoz egy CreatePlayerBox-ot az új játék indításához.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame newGame = new CreatePlayerBox(this, createFunc, startFunc);
		newGame.setVisible(true);
	}

	/**
	 * Lekezeli a felszerelés csere callback-et azáltal, hogy a jelenlegi játékos
	 * cserefüggvényété meghívja a paraméterében kapott értékekkel.
	 * 
	 * @param e1 A felszerelés, amelyre cserélni kíván a játékos.
	 * @param e2 A lecserélendő felszerelés referenciája.
	 */
	public void swapCallback(Equipment e1, Equipment e2) {
		currentPlayer.swapEquipment(e1, e2);
		controller.setWaitingForSkip(false);
		game.nextPlayer();
	}

	/**
	 * Lekezeli a következő játékosra lépés eseményét és lekérdezi az aktuális
	 * játékost.
	 */
	@Override
	public void onNextPlayer() {
		setPlayer(game.getCurrentPlayer());
	}

	/**
	 * Visszaadja az aktuális játék referenciáját.
	 * 
	 * @return az aktuális játék referenciája
	 */
	public Game getActualGame() {
		return game;
	}

	/**
	 * Létrehozza a felszerelés cseréléséhez szükséges ablakot.
	 */
	@Override
	public void onSwap(Equipment e) {
		SwapBox sb = new SwapBox(this, this::swapCallback, currentPlayer, e);
		sb.setVisible(true);
		controller.setWaitingForSkip(true);
	}
}
