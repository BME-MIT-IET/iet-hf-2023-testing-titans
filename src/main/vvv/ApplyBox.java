package vvv;

import java.awt.event.ActionEvent;
import java.util.function.BiConsumer;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * Az ágens felkenéshez szükséges paraméterek kiválasztását megvalósító grafikus
 * felületi elem.
 */
public class ApplyBox extends ActionBox {
	/**
	 * Az ágenst felhasználó callback függvény.
	 */
	private final BiConsumer<Agent, Player> func;
	/**
	 * A megtámadható virológusok listája.
	 */
	private JComboBox<Player> players = new JComboBox<>();
	/**
	 * A felhasználható ágensek listája.
	 */
	private JComboBox<Agent> agents = new JComboBox<>();

	/**
	 * Létrehozza az akció ablakot és a virológushoz tartozó megtámadható
	 * virológusokkal és felhasználható ágensekkel feltölti a listáit, illetve
	 * elmenti a callback függvényt a cselekvéshez.
	 * 
	 * @param parent A szülő ablak referenciája.
	 * @param func   A callback függvény a az ágens felkenéshez.
	 * @param player A virológus, aki éppen cselekszik.
	 */
	public ApplyBox(JFrame parent, BiConsumer<Agent, Player> func, Game game) {
		super(parent, "Válaszd ki a használni kívánt ágenst, és az áldozatot!");
		Player player = game.getCurrentPlayer();
		Field curr = player.getCurrentField();
		for (Player p : curr.getPlayers()) {
			players.addItem(p);
		}
		for (Agent a : player.getAgents()) {
			agents.addItem(a);
		}
		this.func = func;
		this.game = game;

		chooseButton.addActionListener(this);
		setPanelComponent("Választott ágens", agents, "Választott virológus", players);
	}

	/**
	 * Amennyiben van kiválasztott elem mindkét legördülő listában abban meghívja a
	 * callback függvényt a kiválasztott paraméterekkel.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (players.getSelectedItem() != null && agents.getSelectedItem() != null) {
			super.actionPerformed(e);
			func.accept((Agent) agents.getSelectedItem(), (Player) players.getSelectedItem());
		}

	}
}
