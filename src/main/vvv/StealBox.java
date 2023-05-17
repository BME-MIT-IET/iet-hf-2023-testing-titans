package vvv;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * A lopáshoz szükséges paraméterek kiválasztását megvalósító
 * grafikus felületi elem.
 */
public class StealBox extends ActionBox {
	/**
	 * Az anyaglopás callback függvénye.
	 */
	private final transient Consumer<Player> mateStealFunc;
	/**
	 * A felszereléslopás callback függvénye.
	 */
	private final transient Consumer<Player> equiStealFunc;
	/**
	 * Az anyaglopás opció gombja.
	 */
	private final JRadioButton mateStealBtn = new JRadioButton();
	/**
	 * A felszereléslopás opció gombja.
	 */
	private final JRadioButton equiStealBtn = new JRadioButton();
	/**
	 * A meglopható virológusok listája.
	 */
	private final JComboBox<Player> virologists = new JComboBox<>();

	/**
	 * Létrehozza az akció ablakot és a virológus által megtámadható virológusokkal
	 * feltölti a listáját, illetve elmenti a callback függvényeket a cselekvéshez.
	 * 
	 * @param parent        A szülő ablak referenciája.
	 * @param mateStealFunc Az anyaglopás callback függvénye.
	 * @param equiStealFunc A felszerelés callback függvénye.
	 * @param game          A játék példány.
	 */
	public StealBox(JFrame parent, Consumer<Player> mateStealFunc, Consumer<Player> equiStealFunc, Game game) {
		super(parent, "Válaszd ki kitől szeretnél lopni és mit!");
		Player player = game.getCurrentPlayer();
		Field playerField = player.getCurrentField();
		for (Player p : playerField.getPlayers()) {
			if (p != player) {
				virologists.addItem(p);
			}
		}
		this.mateStealFunc = mateStealFunc;
		this.equiStealFunc = equiStealFunc;
		this.game = game;

		ButtonGroup bg = new ButtonGroup();
		bg.add(mateStealBtn);
		bg.add(equiStealBtn);
		JLabel mateStealLabel = new JLabel("Anyag:");
		JLabel equiStealLabel = new JLabel("Felszerelés:");

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(5, 20, 5, 20);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(mateStealLabel, c);
		c.gridx = 1;
		panel.add(mateStealBtn, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(equiStealLabel, c);
		c.gridx = 1;
		panel.add(equiStealBtn, c);

		chooseButton.addActionListener(this);
		setPanelComponent("Választott opció", panel, "Választott virológus", virologists);
	}

	/**
	 * Amennyiben van kiválasztott elem, meghívja a callback függvényt a
	 * kiválasztott paraméterrel. Meghívja az ősosztály ugyanezen nevű függvényét.
	 * Amennyiben az anyaglopást választotta akkor az ahhoz tartozó callback hívódik
	 * meg, ellenkező esetben a másik.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (virologists.getSelectedItem() != null) {
			if (mateStealBtn.isSelected()) {
				super.actionPerformed(e);
				mateStealFunc.accept((Player) virologists.getSelectedItem());
			} else if (equiStealBtn.isSelected()) {
				super.actionPerformed(e);
				equiStealFunc.accept((Player) virologists.getSelectedItem());
			}
		}

	}

}
