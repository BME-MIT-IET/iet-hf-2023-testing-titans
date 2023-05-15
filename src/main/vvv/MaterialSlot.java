package vvv;

/**
 * Ez egy absztrakt osztály, amely az anyagtárolókat reprezentálja. Menedzseli
 * és tárolja a virológus által birtokolt anyagmennyiségét.
 */
public abstract class MaterialSlot extends Slot {
	/**
	 * Az aktuálisan a tárolóban lévő anyagmennyiség.
	 */
	protected int value = 0;

	/**
	 * Létrehozza az anyag tárolót, adott kapacitással.
	 * 
	 * @param maxValue
	 */
	public MaterialSlot(int maxValue) {
		super(maxValue);
	}

	/**
	 * Megadja, hogy összesen mennyi anyag található a tárolóban.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * A maximális anyagszintig tölti a tárolót.
	 */
	public void fillToMax() {
		value = maxValue;
	}

	/**
	 * Az anyag átadást felüldefiniálja és a virológushoz áttölteti az a tartalmát.
	 */
	public void handleMaterialGiving(Player p) {
		p.fillAll(this);
	}

	/**
	 * Az anyag megsemmisítést kezelő metódus, melyben eldobásra kerülnek a
	 * jelenlegi anyagmennyiség.
	 */
	public void handleClearMaterial() {
		value = 0;
	}

	/**
	 * Az anyagtároló telítettségét jelzi, amennyiben a maximális érték megegyezik a
	 * pillanatnyi anyagmennyiséggel.
	 * 
	 * @return A telítettség igazság értéke.
	 */
	public boolean isFull() {
		return value == maxValue;
	}
}
