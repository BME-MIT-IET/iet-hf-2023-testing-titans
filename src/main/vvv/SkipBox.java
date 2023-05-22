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
 * Ez a grafikus felület egy üzenetet ír a felhasználónak, ha nem tud az adott
 * körben lépni.
 */
public class SkipBox extends ModalBox {
	/**
	 * Az üzenet tartalmát elfogadó gomb.
	 */
	private final JButton okButton;
	/**
	 * Az OK gomb lenyomásának hatására meghívódó függvény.
	 */
	private final transient Runnable func;

	/**
	 * Létrehozza az ablakot és elhelyezi rajta a kimaradtál a körből üzenetet és
	 * felhelyezi a gombot és a szöveget az ablakra.
	 * 
	 * @param parent A szülő ablak referenciája.
	 * @param func   Az OK gomb lenyomásának hatására meghívódó függvény.
	 */
	public SkipBox(JFrame parent, Runnable func) {
		super(parent);
		this.func = func;
		parent.setEnabled(true);
		JLabel label = new JLabel("Ebből a körből kimaradtál.");
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
	 * Ha egy játékos nem tud lépni, akkor láthatóvá teszi az ablakot.
	 */
	@Override
	public void onSkip() {
		this.setLocationRelativeTo(parentFrame);
		this.setLocation(parentFrame.getX(), parentFrame.getY() + parentFrame.getHeight() - 200);
		parentFrame.setEnabled(false);
		setVisible(true);
	}

	/**
	 * Az OK gomb lenyomásának hatására meghívódó függvény.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			func.run();
		}
	}

}
