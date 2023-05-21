package vvv;

/**
 * A játéktéren található raktár mezőket megvalósító osztály. Ezeken a mezőkön
 * találhatják meg a virológusok a különböző anyagtípusokat. Továbbá azért is
 * felel, hogy bizonyos időközönként újratöltődjenek az anyagok.
 */
public class StorageField extends Field implements Timeout {
	/** Ebben tárolja az anyagokat. */
	private final Inventory inventory = new Inventory();

	/**
	 * Meghívja őse konstruktorát. Elkészít egy olyan tárolót, ami legfeljebb 3
	 * aminsosavat és ugyanennyi nukleotidet tud tárolni. Feliratkozik a
	 * RoundTimeout-re 3 kör erejéig.
	 */
	public StorageField() {
		super();
		inventory.addSlot(new AminoAcidSlot(3));
		inventory.addSlot(new NucleotideSlot(3));
		inventory.fillToMax();
		RoundTimeout.getInstance().add(this, 3);
	}

	/**
	 * Amikor a virológus rálép a mezőre, akkor ezen keresztül tudja meg a mező,
	 * hogy kinek kell odaadnia a tárolt anyagokat.
	 */
	@Override
	public void collect(Player player) {
		player.destroyMe(this);
		player.give(inventory);
	}

	/** Egy bizonyos idő elteltével újratölti az anyagokat. */
	public void timeout() {
		inventory.fillToMax();
		RoundTimeout.getInstance().add(this, 3);
	}

	/**
	 * A raktárban található összes anyagot megsemmisíti.
	 */
	public void clearAll() {
		inventory.clearMaterials();
	}
}
