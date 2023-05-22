package vvv;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * A ágens kraftoláshoz szükséges paraméterek kiválasztását megvalósító
 * grafikus felületi elem.
 */
public class CraftBox extends ActionBox {
	/**
	 * Az ágens kraftolás callback függvénye.
	 */
	private final transient Consumer<GeneticCode> func;
	/**
	 * A megtanult genetikai kódok listája.
	 */
	private final JComboBox<GeneticCode> geneticCodes = new JComboBox<>();

	/**
	 * Létrehozza az akció ablakot és a virológus által megismert genetikai
	 * kódokkal feltölti a listáját, illetve elmenti a callback függvényt a
	 * cselekvéshez.
	 * 
	 * @param parent A szülő ablak referenciája.
	 * @param func   A kraftolás callback függvénye.
	 * @param game   A játék példány.
	 */
	public CraftBox(JFrame parent, Consumer<GeneticCode> func, Game game) {
		super(parent, "Válaszd ki a lekraftolni kívánt ágenst!");
		Player player = game.getCurrentPlayer();
		for (GeneticCode g : player.getGeneticCodes()) {
			geneticCodes.addItem(g);
		}
		this.func = func;
		this.game = game;

		chooseButton.addActionListener(this);
		setPanelComponent("Választott genetikai kód", geneticCodes);
	}

	/**
	 * Amennyiben van kiválasztott elem, meghívja a callback függvényt a
	 * kiválasztott paraméterrel. Meghívja az ősosztály ugyanezen nevű függvényét.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (geneticCodes.getSelectedItem() != null) {
			super.actionPerformed(e);
			func.accept((GeneticCode) geneticCodes.getSelectedItem());
		}

	}
}
