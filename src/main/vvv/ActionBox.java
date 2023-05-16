package vvv;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Absztrakt osztály, amely általánosan megvalósít egy olyan felugró ablakot,
 * melyen legördülő listából lehet kiválasztani adott cselekvésekhez tartozó
 * paramétereket.
 */
public abstract class ActionBox extends ModalBox {
	/**
	 * A tevékenységet elfogadó gomb.
	 */
	protected JButton chooseButton = new JButton();

	/**
	 * Létrehoz egy általános felépítést a megadott címmel és szülő panellel
	 * rendelkező akció ablakhoz.
	 * 
	 * @param parent A szülő panel referenciája.
	 * @param title  Az akció ablak neve.
	 */
	protected ActionBox(JFrame parent, String title) {
		super(parent);
		chooseButton.setText("Kiválaszt");

		this.setLocationRelativeTo(parent);
		this.setLocation(parent.getX(), parent.getY() + parent.getHeight() - 200);
		this.setSize(parent.getWidth(), 200);
		this.setResizable(false);
		this.setTitle(title);
	}

	/**
	 * Felhelyezi a gombot és a paraméterül kapott list komponenst az ablakra.
	 * 
	 * @param desc A listához tartozó leírás.
	 * @param list A felhelyezendő komponenst.
	 */
	protected void setPanelComponent(String desc, Component list) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel descLabel = new JLabel(desc);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(descLabel, c);
		c.gridx = 1;
		panel.add(list, c);
		c.gridy = 1;
		c.insets = new Insets(20, 20, 20, 20);
		panel.add(chooseButton, c);

		this.setContentPane(panel);
	}

	/**
	 * Felhelyezi a gombot és a paraméterül kapott list komponenseket az ablakra.
	 * 
	 * @param desc1 Az első listához tartozó leírás.
	 * @param list1 Az egyik felhelyezedő komponens.
	 * @param desc2 A második listához tartozó leírás.
	 * @param list2 A másik felhelyezedő komponens.
	 */
	protected void setPanelComponent(String desc1, Component list1, String desc2, Component list2) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel desc1Label = new JLabel(desc1);
		JLabel desc2Label = new JLabel(desc2);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(10, 20, 10, 20);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(desc1Label, c);
		c.gridx = 1;
		panel.add(list1, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(desc2Label, c);
		c.gridx = 1;
		panel.add(list2, c);
		c.gridy = 2;
		panel.add(chooseButton, c);
		this.setContentPane(panel);
	}

	/** Bezárja az ablakot. */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		this.dispose();
	}

}
