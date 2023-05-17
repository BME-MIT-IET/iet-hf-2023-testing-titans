package vvv;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * A játék előkészítését és kezelését valósítja meg. Előkészítés során
 * hozzáadjuk a játékosokat és megkérjük a MapManagert, hogy hozza létre a
 * pályát. Ezután elindíthatjuk a játékot a start függvénnyel. A játék során
 * addig kérjük meg a játékosokat cselekvésre sorban, amíg valamelyikük nem
 * nyer, azaz nem gyűjti össze az összes genetikai kódot. A körök során az
 * eseményekre feliratkozókat is ennek az osztálynak a feladata értesíteni
 * (például új kör kezdete).
 */
public class Game implements Subject<GameObserver> {

	/**
	 * A játék eseményekre feliratkozókat tárolja. Az eseményeket a GameObserver
	 * írja le.
	 */
	private final List<GameObserver> gameObservers = new ArrayList<>();

	/** A generált játékosok tömbje. */
	private final LinkedList<Player> players = new LinkedList<>();

	/** A játék összes mezőjének listája. */
	protected List<Field> fields = new ArrayList<>();

	private ListIterator<Player> currentIterator;
	private static final Random random = new Random();

	private Player player;

	/** Tárolja, hogy a játék meg volt-e állítva. Alapból false. */
	private boolean stopped = false;

	public Game() {
		/** A játék generálásáért felelős MapManager példány. */
		MapManager mapManager = new MapManager();
		mapManager.generate();
		fields = mapManager.getFields();
	}

	/**
	 * Hozzáad egy virológust a játékhoz.
	 * 
	 * @param player a hozzáadni kívánt virológus
	 */
	public void addPlayer(Player player) {
		players.add(player);
		this.player = players.get(0);
		int index = random.nextInt(fields.size());
		player.move(fields.get(index));
	}

	/** Leállítja a játékciklust. */
	public void stop() {
		stopped = true;
	}

	/**
	 * Visszaadja a játékban lévő virológusok listáját.
	 * 
	 * @return players lista
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Megadja, hogy lépésközben leállították-e már a játékmenetet.
	 * 
	 * @return A leállítottság logikai értéke.
	 */
	public boolean isStopped() {
		return stopped;
	}

	public void start() {
		currentIterator = players.listIterator();
		player = currentIterator.next();
		ModelPublisher.getModelPublisher().publishNextPlayer();
		ModelPublisher.getModelPublisher().publishTakeAction();
	}

	/**
	 * Feliratkoztat egy játék esemény megfigyelőt, azaz aki implementálja a
	 * GameObserver interfészt.
	 */
	@Override
	public void subscribe(GameObserver gameObserver) {
		gameObservers.add(gameObserver);
	}

	/** Leiratkoztat egy megfigyelőt. */
	@Override
	public void unsubscribe(GameObserver gameObserver) {
		gameObservers.remove(gameObserver);
	}

	/** Minden feliratkozott Observert értesít az új körről. */
	private void notifyNewRound() {
		for (GameObserver go : gameObservers) {
			go.handleNewRound();
		}
	}

	/** Visszaadja az éppen soron lévő játékost. */
	public Player getCurrentPlayer() {
		return player;
	}

	/**
	 * A következő játékos körét indítja el, és ellenőrzi, hogy nem-e nyert az előző
	 * játékos.
	 * A történésekről értesíti a feliratkozottakat.
	 */
	public void nextPlayer() {
		if (player.getGeneticCodes().size() == 4) {
			ModelPublisher.getModelPublisher().publishWin();
			return;
		}

		do {
			if (!currentIterator.hasNext()) {
				notifyNewRound();
				currentIterator = players.listIterator();
			}

			player = currentIterator.next();
			if (player.isDead()) {
				currentIterator.remove();
			} else {
				break;
			}
		} while (true);

		ModelPublisher.getModelPublisher().publishNextPlayer();

		if (!player.takeAction()) {
			ModelPublisher.getModelPublisher().publishSkip();
			return;
		}

		ModelPublisher.getModelPublisher().publishTakeAction();
	}

}
