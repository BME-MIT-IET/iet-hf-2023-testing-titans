package vvv;

/**
 * A laboratórium mezőket megvalósító osztály. Ezeken a mezőkön találhatják meg
 * a virológusok a genetikai kódokat.
 */
public class LaboratoryField extends Field {
	/** A laborban található genetika kód tárolója. */
	private final Inventory inventory = new Inventory();

	/**
	 * Meghívja őse konstruktorát és hozzáad a mezőhöz egy olyan tárolót, ami
	 * genetikai kódot tud tárolni.
	 */
	public LaboratoryField() {
		super();
		inventory.addSlot(new GeneticCodeSlot());
	}

	/** Megismerteti az adott virológussal az ott található genetikai kódot. */
	@Override
	public void collect(Player player) {
		player.give(inventory);
	}

	/** Beállítja az itt található genetikai kódot a paraméterben kapottra */
	@Override
	public void setGeneticCode(GeneticCode geneticCode) {
		GeneticCodeSlot gcs = new GeneticCodeSlot();
		gcs.addGeneticCode(geneticCode);
		inventory.fillAll(gcs);
	}
}
