package vvv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Ez egy absztrakt osztály, amely modális ablaot valósít meg.
 */
public abstract class ModalBox extends JFrame implements ActionListener, ModelObserver {
	/**
	 * A szűlő ablak referenciája.
	 */
	protected JFrame parentFrame;
	/**
	 * A játék példány.
	 */
	protected Game game = null;

	/**
	 * Létrehozza a modális ablakot és elérhetetlenné teszi a szűlő ablakot.
	 * 
	 * @param parent A szülő ablak referenciája.
	 */
	protected ModalBox(JFrame parent) {
		this.parentFrame = parent;
		parent.setEnabled(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Visszaállítja a szülő ablakot elérhető állapotba és bezárja a modális
	 * ablakot.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parentFrame.setEnabled(true);
		this.setVisible(false);
	}

}
