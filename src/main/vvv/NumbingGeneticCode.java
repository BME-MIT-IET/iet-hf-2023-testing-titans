package src.main.vvv;

/**
 * Azt a genetikai kódot reprezentálja, amiből bénulást okozó ágenst lehet
 * előállítani.
 */
public class NumbingGeneticCode extends GeneticCode {
	/** Az osztály egyetlen példányát tárolja. */
	private static NumbingGeneticCode numbingGeneticCodeInstance = new NumbingGeneticCode();

	/** Meghívja őse konstruktorát 2, 3 paraméterekkel. */
	private NumbingGeneticCode() {
		super(2, 3);
	}

	/** Visszaadja az osztály egyetlen példányát. */
	public static NumbingGeneticCode getInstance() {
		return numbingGeneticCodeInstance;
	}

	/**
	 * Előállít egy NumbingAgent típusú objektumot, amit elhelyez a paraméterként
	 * megkapott tárolóban.
	 * 
	 * @return a létrehozott ágens
	 */
	public Agent createAgent(Inventory inventory) {
		boolean hasEverything = inventory.checkMaterial(inventoryToFill);
		NumbingAgent a = null;
		if (hasEverything) {
			a = new NumbingAgent();
			AgentSlot as = new AgentSlot();
			as.addAgent(a);
			inventory.fillAll(as);
		}
		inventoryToFill.clearMaterials();
		return a;
	}

	/**
	 * Stringbe csomagolva adja vissza, hogy milyen típusú ágenst tud előállítani.
	 */
	@Override
	public String type() {
		return "numb";
	}

	/** Visszaadja a genetikai kód nevét. */
	@Override
	public String toString() {
		return "Bénító";
	}
}
