package vvv;

/**
 * A balta felszerelést reprezentálja. Ezzel az eszközzel lehet megtámadni egy
 * másik virológust.
 */
public class AxeEquipment extends Equipment {
	/** Azt reprezentálja, hogy el lett-e használva a balta. */
	private boolean used = false;

	/**
	 * Amikor egy játékos támadna, akkor reagál rá a balta.
	 * 
	 * @return igaz
	 */
	@Override
	public boolean handleAttack(Player p) {
		if (!used) {
			p.killedBy();
			used = true;
		}
		return true;
	}

	/**
	 * Megadja, hogy el lett-e már használva a balta.
	 * 
	 * @return igaz, ha el volt használva, különben hamis
	 */
	public boolean isUsed() {
		return used;
	}

	/** Visszaadja a felszerelés nevét. Csak megjelenítéshez kell! */
	@Override
	public String toString() {
		String usedStr = used ? " (-)" : " (+)";
		return "Balta" + usedStr;
	}
}
