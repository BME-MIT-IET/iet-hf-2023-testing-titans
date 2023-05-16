package vvv;

/**
 * Felelőssége, hogy hatására elfelejtse az összes megismert genetikai kódot
 * azon virológus, akire ezt alkalmazták.
 */
public class ForgetAgentEffect extends AgentEffect {
	/** Feliratkozik a RoundTimeout-ra 0 kör erejéig. */
	public ForgetAgentEffect() {
		super();
		RoundTimeout.getInstance().add(this, 0);
	}

	/**
	 * Lekéri a virológustól a genetikai kódok tárolóját és törli annak tartalmát.
	 */
	@Override
	public void onAttach(Player player) {
		player.clearGeneticCodes();
	}

	/** Visszaadja a hatás nevét. */
	@Override
	public String toString() {
		return "Felejtő";
	}
}
