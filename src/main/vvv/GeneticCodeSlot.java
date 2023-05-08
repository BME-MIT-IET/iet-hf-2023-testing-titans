package src.main.vvv;

import java.util.ArrayList;
import java.util.List;

/**
 * Menedzseli és tárolja a virológus által megismert genetikai kódokat.
 */
public class GeneticCodeSlot extends Slot {
	/**
	 * Ebben az objektumban tárolja el a virológus által megismert genetikai
	 * kódokat.
	 */
	private List<GeneticCode> geneticCodes = new ArrayList<GeneticCode>();

	/**
	 * Létrehozza a tárolót, és beállítja a kapacitását 4-re.
	 */
	public GeneticCodeSlot() {
		super(4);
	}

	/**
	 * Megadja a megismert genetikai kódok számát.
	 */
	public int getValue() {
		int size = geneticCodes.size();
		return size;
	}

	/**
	 * Megpróbálja az adott tárolóból az összes genetikai kód referenciáját a saját
	 * maga maximális kapacitásáig áthelyezni.
	 */
	public void fill(Slot fromSlot) {
		fromSlot.fillIt(this);
	}

	/**
	 * A paraméterül kapott genetikai kód tárolót megtölti a saját magában lévő
	 * genetikai kódokkal.
	 */
	public void fillIt(GeneticCodeSlot gs) {
		for (GeneticCode g : geneticCodes) {
			if (!gs.geneticCodes.contains(g)) {
				gs.geneticCodes.add(g);
			}
		}
	}

	/**
	 * Lekezeli a megismert genetikai kódok törlését és törli azok referenciáját a
	 * tárolóból.
	 */
	public void handleClearMemory() {
		geneticCodes.clear();
	}

	/**
	 * Hozzáadja a megtanult genetikai kódot a tárolóhoz.
	 * 
	 * @param geneticCode A hozzáadandó genetikai kód.
	 */
	public void addGeneticCode(GeneticCode geneticCode) {
		geneticCodes.add(geneticCode);
	}

	/**
	 * Amennyiben megtanulta az összes genetikai kódot, akkor igazzal tér vissza.
	 * 
	 * @return A megtanultság teljességének logikai értéke.
	 */
	public boolean isFull() {
		return geneticCodes.size() == 4;
	}

	/**
	 * A tárolóban lévő első genetikai kódot adja vissza.
	 * 
	 * @return Az első genetikai kód referenciája.
	 */
	public GeneticCode getGeneticCode() {
		if (geneticCodes.size() == 0)
			return null;
		return geneticCodes.get(0);
	}

	/**
	 * A tárolóban lévő genetikai kódok listáját adja vissza.
	 * 
	 * @return A genetikai kódok listájának referenciája.
	 */
	public List<GeneticCode> getGeneticCodes() {
		return geneticCodes;
	}
}
