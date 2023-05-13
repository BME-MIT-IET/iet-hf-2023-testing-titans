package vvv;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Az 5 cselekvés kiválasztását megjelenítő osztály.
 */
public class TakeActionBox extends ModalBox {

	/**
	 * A lépés event gombja.
	 */
	private JButton moveButton;
	/**
	 * A kraftolás event gombja.
	 */
	private JButton craftButton;
	/**
	 * A ágens felkenés event gombja.
	 */
	private JButton applyButton;
	/**
	 * A lebaltázás event gombja.
	 */
	private JButton hitButton;
	/**
	 * A lopás event gombja.
	 */
	private JButton stealButton;

	/**
	 * A cselekvés callback-ek listája.
	 */
	private List<Runnable> funcs = new ArrayList<>();

	/**
	 * Beállít egy gombot a megadott képpel és formázással.
	 * 
	 * @param btn
	 *            A formázandó gomb referenciája.
	 * @param pic A gomb képének elérési útvonala.
	 */
	private void setButton(JButton btn, String pic) {
		ImageIcon img = new ImageIcon(pic);
		btn.setIcon(img);
		btn.setBackground(new Color(169, 209, 142));
		btn.setHorizontalTextPosition(JButton.CENTER);
		btn.setVerticalTextPosition(JButton.CENTER);
		btn.setMargin(new Insets(0, 0, 0, 0));
		btn.addActionListener(this);
	}

	/**
	 * Létrehozza az akció ablakot az 5 cselekvés gombbal és elmenti azokhoz a
	 * callback függvényeket.
	 * 
	 * @param parent    A szülő ablak referenciája.
	 * @param moveFunc  A lépés callback függvénye.
	 * @param craftFunc A kraftolás callback függvénye.
	 * @param applyFunc Az ágens felkenés callback függvénye.
	 * @param hitFunc   A lebaltázás felkenés callback függvénye.
	 * @param stealFunc A lopás callback függvénye.
	 */
	public TakeActionBox(MainFrame parent, Runnable moveFunc, Runnable craftFunc, Runnable applyFunc, Runnable hitFunc,
			Runnable stealFunc) {
		super(parent);
		funcs.add(moveFunc);
		funcs.add(craftFunc);
		funcs.add(applyFunc);
		funcs.add(hitFunc);
		funcs.add(stealFunc);
		parent.setEnabled(true);
		moveButton = new JButton();
		setButton(moveButton, "./pics/move_64.png");
		craftButton = new JButton();
		setButton(craftButton, "./pics/craft_64.png");
		applyButton = new JButton();
		setButton(applyButton, "./pics/apply_64.png");
		hitButton = new JButton();
		setButton(hitButton, "./pics/hit_64.png");
		stealButton = new JButton();
		setButton(stealButton, "./pics/steal_64.png");

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 10);
		panel.add(moveButton, c);
		c.gridx = 1;
		panel.add(craftButton, c);
		c.gridx = 2;
		panel.add(applyButton, c);
		c.gridx = 3;
		panel.add(hitButton, c);
		c.gridx = 4;
		panel.add(stealButton, c);

		this.setContentPane(panel);
		this.setLocationRelativeTo(parent);
		this.setLocation(parent.getX(), parent.getY() + parent.getHeight() - 200);
		this.setSize(parent.getWidth(), 200);
		this.setResizable(false);
		this.setTitle("Válassz az alábbi cselekvések közül!");

		ModelPublisher.getModelPublisher().subscribe(this);
	}

	/**
	 * Attól függően, hogy melyik cselekvés gombot választotta meghívja az adott
	 * cselekvéshez tartozó callback függvényt. Továbbá meghívja az ős ugyanilyen
	 * nevű függvényét.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == moveButton) {
			super.actionPerformed(e);
			funcs.get(0).run();
		} else if (e.getSource() == craftButton) {
			super.actionPerformed(e);
			funcs.get(1).run();
		} else if (e.getSource() == applyButton) {
			super.actionPerformed(e);
			funcs.get(2).run();
		} else if (e.getSource() == hitButton) {
			super.actionPerformed(e);
			funcs.get(3).run();
		} else if (e.getSource() == stealButton) {
			super.actionPerformed(e);
			funcs.get(4).run();
		}
	}

	/**
	 * Beállítja a panelen megjelenő gombokat attól függően, hogy az aktuálisan lépő
	 * virológus milyen cselekvéseket hajthat egyáltalán végre.
	 */
	private void setButtonsState() {
		MainFrame frame = (MainFrame) parent;
		Player reference = frame.getActualGame().getCurrentPlayer();
		moveButton.setEnabled(true);
		craftButton.setEnabled(true);
		applyButton.setEnabled(true);
		hitButton.setEnabled(false);
		stealButton.setEnabled(true);
		for (Equipment e : reference.getEquipments()) {
			if (e.toString().contains("Balta")) {
				hitButton.setEnabled(true);
				break;
			}
		}

		if (reference.getCurrentField().getPlayers().size() == 1) {
			hitButton.setEnabled(false);
			stealButton.setEnabled(false);
		}
		if (reference.getGeneticCodes().size() == 0) {
			craftButton.setEnabled(false);
		}
		if (reference.getAgents().size() == 0) {
			applyButton.setEnabled(false);
		}

	}

	/**
	 * Ha a játékosnak cselekvést kell választania, akkor láthatóvá teszi az
	 * ablakot.
	 */
	@Override
	public void onTakeAction() {
		this.setButtonsState();
		this.setLocationRelativeTo(parent);
		this.setLocation(parent.getX(), parent.getY() + parent.getHeight() - 200);
		setVisible(true);
		parent.setEnabled(false);
	}

}
