package vvv;

/**
 * Menedzseli és tárolja a virológus által birtokolt nukleotid mennyiségét.
 */
public class NucleotideSlot extends MaterialSlot {

	/**
	 * Létrehozza a tárolót és beállítja a maximális kapacitását.
	 * 
	 * @param maxValue A tároló maximális kapacitása.
	 */
	public NucleotideSlot(int maxValue) {
		super(maxValue);
	}

	/**
	 * Megpróbálja az adott tárolóból az összes nukleotidot a saját maga maximális
	 * kapacitásáig áthelyezni.
	 */
	public void fill(Slot fromSlot) {
		fromSlot.fillIt(this);
	}

	/**
	 * A paraméterül kapott nukleotid tárolót megtölti a saját magában tárolt
	 * mennyiséggel, ameddig az meg nem telik teljesen, vagy a tárolóból el nem fogy
	 * az anyag.
	 */
	public void fillIt(NucleotideSlot nucs) {
		int nucsDiff = nucs.maxValue - nucs.value;
		nucs.value = Math.min(nucs.value + value, nucs.maxValue);
		value = Math.max(0, value - nucsDiff);
	}

	/**
	 * Visszaadja a tárolóban tárolható maximális nukleotid mennyiséget.
	 * 
	 * @return A tárolóban tárolható maximális nukleotid mennyiség.
	 */
	public int getMaxNucleotideCount() {
		return maxValue;
	}

	/**
	 * Visszaadja a tárolóban lévő nukleotid mennyiséget.
	 * 
	 * @return A tárolóban lévő nukleotid mennyiség.
	 */
	public int getNucleotideCount() {
		return value;
	}
}
