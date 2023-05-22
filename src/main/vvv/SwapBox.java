package vvv;

import java.awt.event.ActionEvent;
import java.util.function.BiConsumer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A csereléshez szükséges paraméterek kiválasztását megvalósító grafikus
 * felületi elem.
 */
public class SwapBox extends ActionBox {
	/**
	 * A felszerelés csere callback függvénye.
	 */
	private final transient BiConsumer<Equipment, Equipment> func;
	/**
	 * A lecserélhető felszerelések listája.
	 */
	private final JComboBox<Equipment> equipments = new JComboBox<>();
	/**
	 * A felvehető felszerelés referenciája.
	 */
	private final transient Equipment swapEqui;

	/**
	 * Létrehozza az akció ablakot és a virológushoz tartozó felszerelésekkel
	 * feltölti a listáit, illetve
	 * elmenti a callback függvényt a cselekvéshez.
	 * 
	 * @param parent A szülő ablak referenciája.
	 * @param func   A csere callback függvénye.
	 * @param player A virológus referenciája.
	 * @param e      A felvehető felszerelés referenciája.
	 */
	public SwapBox(JFrame parent, BiConsumer<Equipment, Equipment> func, Player player, Equipment e) {
		super(parent, "Válaszd ki a lecserélni kívánt felszerelést!");
		swapEqui = e;
		for (Equipment eq : player.getEquipments()) {
			equipments.addItem(eq);
		}
		this.func = func;
		chooseButton.addActionListener(this);
		setPanelComponent("Felvehető felszerelés:", new JLabel(e.toString()), "Választott felszerelés", equipments);
	}

	/**
	 * Amennyiben van kiválasztott elem, meghívja a callback függvényt a
	 * kiválasztott paraméterrel. Meghívja az ősosztály ugyanezen nevű függvényét.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (equipments.getSelectedItem() != null) {
			super.actionPerformed(e);
			func.accept((Equipment) equipments.getSelectedItem(), swapEqui);
		}

	}
}
