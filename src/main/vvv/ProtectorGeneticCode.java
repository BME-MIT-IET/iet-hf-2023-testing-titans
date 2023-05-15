package vvv;

/**
 * Azt a genetikai kódot reprezentálja, amiből védelmező ágenst lehet
 * előállítani.
 */
public class ProtectorGeneticCode extends GeneticCode {
	/** Az osztály egyetlen példányát tárolja. */
	private static ProtectorGeneticCode protectorGeneticCodeInstance = new ProtectorGeneticCode();

	/** Meghívja őse konstruktorát 3, 1 paraméterekkel. */
	private ProtectorGeneticCode() {
		super(3, 1);
	}

	/** Visszaadja az osztály egyetlen példányát. */
	public static ProtectorGeneticCode getInstance() {
		return protectorGeneticCodeInstance;
	}

	/**
	 * A védelmező vakcinához tartozó ágenst készíti el, amennyiben az elkészítéshez
	 * szükséges anyagmennyiség a rendelkezésére áll. Az elkészített ágenst
	 * hozzáadja a virológus tárolójához.
	 * 
	 * @return a létrehozott ágens
	 */
	public Agent createAgent(Inventory inventory) {
		boolean hasEverything = inventory.checkMaterial(inventoryToFill);
		ProtectorAgent a = null;
		if (hasEverything) {
			a = new ProtectorAgent();
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
		return "prot";
	}

	/** Visszaadja a genetikai kód nevét. */
	@Override
	public String toString() {
		return "Védelmező";
	}
}
