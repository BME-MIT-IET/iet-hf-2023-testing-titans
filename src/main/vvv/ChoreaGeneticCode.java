package vvv;

/**
 * A vitustánc vírushoz tartozó ágenst készíti el, amelyet hozzáad a virológus
 * tárolójához. Felelőssége, hogy csak akkor engedje elkészíti az ágenst, ha a
 * hozzá szükséges anyagok mind a rendelkezésére állnak.
 */
public class ChoreaGeneticCode extends GeneticCode {
	private static ChoreaGeneticCode choreaGeneticCodeInstance = new ChoreaGeneticCode();

	/** Meghívja őse konstruktorát 2, 2 paraméterekkel. */
	private ChoreaGeneticCode() {
		super(2, 2);
	}

	/** Visszaadja az osztály egyetlen példányát. */
	public static ChoreaGeneticCode getInstace() {
		return choreaGeneticCodeInstance;
	}

	/**
	 * A vitustánc vírushoz tartozó ágenst készíti el, amennyiben az elkészítéshez
	 * szükséges anyagmennyiség a rendelkezésére áll. Az elkészített ágenst
	 * hozzáadja a virológus tárolójához.
	 * 
	 * @return a létrehozott ágens
	 */
	public Agent createAgent(Inventory inventory) {
		boolean hasEverything = inventory.checkMaterial(inventoryToFill);
		ChoreaAgent a = null;
		if (hasEverything) {
			a = new ChoreaAgent();
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
		return "chor";
	}

	/** Visszaadja a genetikai kód nevét. */
	@Override
	public String toString() {
		return "Vitustánc";
	}
}
