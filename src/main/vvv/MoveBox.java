package vvv;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * A lépéshez szükséges paraméterek kiválasztását megvalósító grafikus felületi
 * elem.
 */
public class MoveBox extends ActionBox {
	/**
	 * A lépés callback függvénye.
	 */
	private final transient Consumer<Field> func;
	/**
	 * A választható mezők listája.
	 */
	private final transient JComboBox<Field> fields = new JComboBox<>();

	/**
	 * Létrehozza az akció ablakot és a virológus által megközelíthető mezőkkel
	 * feltölti a listáját, illetve elmenti a callback függvényt a cselekvéshez.
	 * 
	 * @param parent A szülő ablak referenciája.
	 * @param func   A lépés callback függvénye.
	 * @param game   A játék példány.
	 */
	public MoveBox(JFrame parent, Consumer<Field> func, Game game) {
		super(parent, "Válaszd ki a célmezőt!");
		Player player = game.getCurrentPlayer();
		Field curr = player.getCurrentField();
		for (Field f : curr.getNeighbors()) {
			fields.addItem(f);
		}
		this.func = func;
		this.game = game;

		chooseButton.addActionListener(this);
		setPanelComponent("Választott mező", fields);
	}

	/**
	 * Amennyiben van kiválasztott elem, meghívja a callback függvényt a
	 * kiválasztott paraméterrel. Meghívja az ősosztály ugyanezen nevű függvényét.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (fields.getSelectedItem() != null) {
			super.actionPerformed(e);
			func.accept((Field) fields.getSelectedItem());
		}

	}

	public JComboBox<Field> getFields() {
		return fields;
	}
}
