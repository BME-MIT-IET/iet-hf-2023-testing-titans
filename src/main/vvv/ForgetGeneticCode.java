package vvv;

/**
 * A genetikai kódokat elfelejtő vírushoz tartozó ágenst készíti el, amelyet
 * hozzáad a virológus tárolójához. Felelőssége, hogy csak akkor engedje
 * elkészíti az ágenst, ha a hozzá szükséges anyagok mind a rendelkezésére
 * állnak.
 */
public class ForgetGeneticCode extends GeneticCode {
	/** Az osztály egyetlen példányát tárolja. */
	private static ForgetGeneticCode forgetGeneticCodeInstance = new ForgetGeneticCode();

	/** Meghívja őse konstruktorát 4, 4 paraméterekkel. */
	private ForgetGeneticCode() {
		super(4, 4);
	}

	/** Visszaadja az osztály egyetlen példányát. */
	public static ForgetGeneticCode getInstance() {
		return forgetGeneticCodeInstance;
	}

	/**
	 * A genetikai kódokat elfelejtő vírushoz tartozó ágenst készíti el, amelyet
	 * hozzáad a virológus tárolójához. Felelőssége, hogy csak akkor engedje
	 * elkészíti az ágenst, ha a hozzá szükséges anyagok mind a rendelkezésére
	 * állnak.
	 * 
	 * @return a létrehozott ágens
	 */
	public Agent createAgent(Inventory inventory) {
		boolean hasEveryThing = inventory.checkMaterial(inventoryToFill);
		ForgetAgent a = null;
		if (hasEveryThing) {
			a = new ForgetAgent();
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
		return "forg";
	}

	/** Visszaadja a genetikai kód nevét. */
	@Override
	public String toString() {
		return "Felejtő";
	}
}
