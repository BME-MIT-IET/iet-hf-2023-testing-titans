package vvv;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ez a grafikus felület egy üzenetet ír a felhasználónak, ha megnyerte a
 * játékot.
 */
public class WinBox extends ModalBox {
	/**
	 * Az üzenetet elfogadó gomb.
	 */
	private JButton okButton;
	/**
	 * A játék bezárást megvalósító callback függvényt.
	 */
	private Runnable exitFunc;

	/**
	 * Létrehozza a győezelem üzenet ablakot és kiírja a győezelm szöveget.
	 * 
	 * @param parent   A szülő ablak referenciája.
	 * @param exitFunc A játék bezárást megvalósító callback függvény.
	 */
	public WinBox(JFrame parent, Runnable exitFunc) {
		super(parent);
		parent.setEnabled(true);
		this.exitFunc = exitFunc;
		JLabel label = new JLabel("Gratulálunk megnyerted a játékot.");
		okButton = new JButton("OK");
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);
		c.gridy = 1;
		c.insets = new Insets(20, 20, 20, 20);
		panel.add(okButton, c);
		okButton.addActionListener(this);
		this.setContentPane(panel);
		this.setLocationRelativeTo(parent);
		this.setLocation(parent.getX(), parent.getY() + parent.getHeight() - 200);
		this.setSize(parent.getWidth(), 200);
		this.setResizable(false);

		ModelPublisher.getModelPublisher().subscribe(this);
	}

	/**
	 * Az OK gomb lenyomás hatására meghívódik a játékot befejező callback függvény.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		exitFunc.run();
	}

	/**
	 * Ha az aktuális játékos megnyeri a játékot, láthatóvá teszi az ablakot.
	 */
	@Override
	public void onWin() {
		this.setLocationRelativeTo(parentFrame);
		this.setLocation(parentFrame.getX(), parentFrame.getY() + parentFrame.getHeight() - 200);
		parentFrame.setEnabled(false);
		setVisible(true);
	}
}