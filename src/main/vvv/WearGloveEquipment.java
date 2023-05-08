package src.main.vvv;

/**
 * A kesztyű tulajdonosát ágenssel megtámadó virológusra visszafejti az ágens
 * hatását, ezen kívül tárolja, hogy a kesztyű még hányszor használható, mielőtt
 * megszűnik a kesztyű.
 */
public class WearGloveEquipment extends GloveEquipment {

	/**
	 * Azt tárolja, hogy hányszor használható még a kesztyű, mielőtt megsemmisülne.
	 */
	private int lifeTime;

	/** Konstruktor, lifeTime értékét 3-ra állítja be. */
	public WearGloveEquipment() {
		super();
		lifeTime = 3;
	}

	/**
	 * Az ágensel történő kenésre reagál.
	 * 
	 * @param player ez a virológus kente fel a kesztyű viselőjét
	 * @param agent  ezzel az ágenssel kente fel a virológust
	 * @return true
	 */
	@Override
	public boolean handleAnointedBy(Player player, Agent agent) {
		super.handleAnointedBy(player, agent);
		lifeTime--;
		if (lifeTime == 0) {
			this.player.throwAwayEquipment(this);
		}
		return true;
	}

	/** Visszaadja a felszerelést nevét. */
	@Override
	public String toString() {
		return "Kesztyű (" + Integer.toString(lifeTime) + ")";
	}
}
