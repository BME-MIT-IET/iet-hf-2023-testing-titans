package src.main.vvv;

/**
 * A játéktéren a laboratórium mezőkön megtalálható genetikai kódokat
 * megvalósító absztrakt osztály. A játék megnyeréséhez ezeket kell letapogatni
 * a virológusoknak. A letapogatott genetikai kódok ágensek készítéséhez
 * használhatók.
 */
public abstract class GeneticCode {
	/**
	 * A genetikai kódból előállítható ágenshez szükséges anyagok listáját tárolja.
	 */
	protected Inventory inventoryToFill = new Inventory();

	/** Beállítja, hogy az ágens előállításához mennyi anyagra lesz szükség. */
	public GeneticCode(int aminoCount, int nucleoCount) {
		super();
		inventoryToFill.addSlot(new AminoAcidSlot(aminoCount));
		inventoryToFill.addSlot(new NucleotideSlot(nucleoCount));
	}

	/**
	 * Absztrakt függvény, amelynek nincs alap implementációja, a leszármazottak az
	 * ágensek létrehozására használják a felüldefiniált változatait.
	 */
	public abstract Agent createAgent(Inventory inventory);

	/**
	 * Stringbe csomagolva adja vissza, hogy milyen típusú ágenst tud előállítani.
	 */
	public abstract String type();

	/** Visszaadja a genetikai kód nevét. */
	public abstract String toString();
}
