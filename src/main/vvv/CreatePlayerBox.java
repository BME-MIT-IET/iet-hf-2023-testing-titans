package src.main.vvv;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Az új játék létrejozásakor ennek a segítségével lehet felvenni a
 * virológusokat és elindítani egy új játékot.
 */
public class CreatePlayerBox extends ModalBox {
	/**
	 * A virológus nevének megadásához szükséges beviteli mező.
	 */
	private JTextField playerNameBox = new JTextField();
	/**
	 * A virológus hozzáadáshoz szükséges gomb.
	 */
	private JButton addButton = new JButton("Hozzáadás");
	/**
	 * A játék indításához szükséges gomb.
	 */
	private JButton startButton = new JButton("Játék indítása");
	/**
	 * A virológus létrehozás callback függvény.
	 */
	private Consumer<String> createFunc;
	/**
	 * A játék indítás callback függvénye.
	 */
	private Runnable startFunc;

	/**
	 * Létrehozza a virológust létrehozó ablakot és elhelyezi rajta beviteli mezőt
	 * és a két gombot egymás alá. Továbbá elmenti a callback függvényeket.
	 * 
	 * @param parent     A szülő ablak referenciája.
	 * @param createFunc A virológust létrehozó callback függvény.
	 * @param startFunc  A játék indítás callback függvénye.
	 */
	public CreatePlayerBox(JFrame parent, Consumer<String> createFunc, Runnable startFunc) {
		super(parent);
		this.createFunc = createFunc;
		this.startFunc = startFunc;
		startButton.setEnabled(false);

		this.setTitle("Add hozzá a játékosokat!");

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Játékos neve"), c);
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(playerNameBox, c);
		c.gridx = 0;
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		panel.add(addButton, c);
		c.gridx = 1;
		panel.add(startButton, c);

		startButton.addActionListener(this);
		addButton.addActionListener(this);

		this.setContentPane(panel);
		this.setLocationRelativeTo(parent);
		this.setSize(400, 200);
		this.setLocation(parent.getX(), parent.getY() + 50);
		this.setResizable(false);
	}

	/**
	 * Amennyiben van kiválasztott elem, meghívja a megfelelő callback függvényt a
	 * kiválasztott paraméterrel. Meghívja az ősosztály ugyanezen nevű függvényét. A
	 * start gomb megnyomásakor a játékot indítja el, míg a hozzáadás gombnál egy
	 * virológust hoz létre a callback függények segítségével.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			super.actionPerformed(e);
			startFunc.run();
		} else if (e.getSource() == addButton) {
			if (playerNameBox.getText().length() == 0) {
				JOptionPane.showMessageDialog(this,
						"Megadott játékos név túl rövid!",
						"Név hiba",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			startButton.setEnabled(true);
			createFunc.accept(playerNameBox.getText());
			playerNameBox.setText("");
		}
	}

}
