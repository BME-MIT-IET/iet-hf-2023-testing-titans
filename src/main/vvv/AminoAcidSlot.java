package src.main.vvv;

/**
 * Menedzseli és tárolja a virológus által birtokolt aminosav mennyiségét.
 */
public class AminoAcidSlot extends MaterialSlot {

	/**
	 * Az adott maximális kapacitással rendelkező tárolót hoz létre.
	 * 
	 * @param maxValue
	 *                 A tároló maximális kapacitása.
	 */
	public AminoAcidSlot(int maxValue) {
		super(maxValue);
	}

	/**
	 * Megpróbálja az adott tárolóból az összes aminosavat a saját maga maximális
	 * kapacitásáig áthelyezni.
	 */
	public void fill(Slot fromSlot) {
		fromSlot.fillIt(this);
	}

	/**
	 * A paraméterül kapott aminosav tárolót megtölti a benne található aminosavval
	 * és a
	 * saját magából pedig eltávolítja azokat.
	 */
	public void fillIt(AminoAcidSlot ams) {
		int amsDiff = ams.maxValue - ams.value;
		ams.value = Math.min(ams.value + value, ams.maxValue);
		value = Math.max(0, value - amsDiff);
	}

	/**
	 * Visszaadja a tárolóban lévő animosav mennyiséget.
	 * 
	 * @return A tárolóban lévő aminosav mennyiség.
	 */
	public int getAminoAcidCount() {
		return value;
	}

	/**
	 * Visszaadja a tárolóban tárolható maximális animosav mennyiséget.
	 * 
	 * @return A tárolóban tárolható maximális aminosav mennyiség.
	 */
	public int getMaxAminoAcidCount() {
		return maxValue;
	}

}
