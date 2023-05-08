package src.main.vvv;

/**
 * Olyan ősosztály, melynek leszármazott osztályai reagálhatnak a virológussal
 * történt eseményekre.
 */
public abstract class Effect {
	/** A virológus, akire kifejti a hatását az adott ágens vagy felszerelés */
	protected Player player;

	/**
	 * Jelzi a paraméterül kapott virológus számára, hogy hatással rendelkező dolgot
	 * helyeztek el rajta.
	 */
	public void onAttach(Player player) {
		this.player = player;
	}

	/**
	 * Ezzel jelez a tulajdonosnak, hogy a kapott plusz hatások megszűnnek.
	 */
	public void onDetach() {
		this.player = null;
	}

	/**
	 * Alapértelmezetten hamis értékkel tér vissza, amennyiben a virológusra ágenst
	 * próbálnak meg felkenni, és az adott ágens hatás vagy felszerelés er- re nem
	 * kíván reagálni.
	 */
	public boolean handleAnointedBy(Player player, Agent agent) {
		return false;
	}

	/**
	 * Alapértelmezetten igazzal tér vissza, amennyiben a virológustól lopni
	 * akarnak.
	 */
	public boolean handleStealing() {
		return true;
	}

	/**
	 * Alapértelmezetten hamis értékkel tér vissza, amennyiben valamilyen
	 * cselekedetet akar a virológus végrehajtani, nem akadályozza meg abban.
	 */
	public boolean handleTakeAction() {
		return false;
	}

	/** Reagál, ha egy játékost támadás ért és aktív a hatás. */
	public boolean handleAttack(Player p) {
		return false;
	}

	/**
	 * Amennyiben raktárra lép, akkor reágal arra, hogy tönkre tudja-e tenni a
	 * raktár tartalmát.
	 */
	public boolean handleDestroying(StorageField sf) {
		return false;
	}

	/** Medvevírussal való fertőzésre reagál. */
	public boolean handleInfection() {
		return false;
	}

	/** Visszaadja a hatás nevét. Csak megjelnítéshez! */
	@Override
	public abstract String toString();
}
