package vvv;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * A baltával való támadáshoz szükséges paraméterek kiválasztását megvalósító
 * grafikus
 * felületi elem.
 */
public class AttackBox extends ActionBox {
	/**
	 * A támadás callback függvénye.
	 */
	private final transient Consumer<Player> func;
	/**
	 * A megtámadható virológusok listája.
	 */
	private final JComboBox<Player> virologists = new JComboBox<>();

	/**
	 * Létrehozza az akció ablakot és a virológushoz tartozó megtámadható
	 * virológusokkal feltölti a listáját, illetve
	 * elmenti a callback függvényt a cselekvéshez.
	 * 
	 * @param parent A szülő ablak.
	 * @param func   A támadás callback függvénye.
	 * @param game   A játék controllere.
	 */
	public AttackBox(JFrame parent, Consumer<Player> func, Game game) {
		super(parent, "Válaszd ki a megtámadni kívánt virológust!");
		Player player = game.getCurrentPlayer();
		Field playerField = player.getCurrentField();
		for (Player p : playerField.getPlayers()) {
			if (p != player) {
				virologists.addItem(p);
			}
		}
		this.func = func;
		this.game = game;

		chooseButton.addActionListener(this);
		setPanelComponent("Választott virológus", virologists);
	}

	/**
	 * Amennyiben van kiválasztott elem, meghívja a callback függvényt a
	 * kiválasztott paraméterekkel. Meghívja az ősosztály ugyanezen nevű függvényét.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (virologists.getSelectedItem() != null) {
			super.actionPerformed(e);
			func.accept((Player) virologists.getSelectedItem());
		}

	}

}
