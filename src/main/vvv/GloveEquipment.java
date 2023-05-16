package vvv;

/**
 * A kesztyű tulajdonosát ágenssel megtámadó virológusra visszafejti az ágens
 * hatását.
 */
public class GloveEquipment extends Equipment {
	/**
	 * A tulajdonosára felkent ágens hatását a felkenő virológusra ráhelyezi, a
	 * művelet sikerességét jelezve igazzal tér vissza.
	 */
	@Override
	public boolean handleAnointedBy(Player player, Agent agent) {
		player.anointedByFinal(agent);
		return true;
	}

	/** Visszaadja a felszerelés nevét. */
	@Override
	public String toString() {
		return "Kesztyű";
	}
}