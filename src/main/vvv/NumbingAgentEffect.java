package vvv;

/**
 * Felelőssége, hogy hatása lejártáig lebénított állapotban tartsa azt a
 * virológust, akire éppen kifejti a hatását, eközben nem enged meg más
 * tevékenységeket végrehajtania.
 */
public class NumbingAgentEffect extends AgentEffect {
	/** Feliratkozik a RoundTimeout-ra 3 kör erejéig. */
	public NumbingAgentEffect() {
		super();
		RoundTimeout.getInstance().add(this, 3);
	}

	/** Hamissal tér vissza, ilyenkor lehet lopni a virológustól. */
	public boolean handleStealing() {
		return false;
	}

	/**
	 * Igazzal tér vissza, ilyenkor nem engedi cselekedni a virológust.
	 */
	public boolean handleTakeAction() {
		return true;
	}

	/** Visszaadja a hatás nevét. */
	@Override
	public String toString() {
		return "Bénító";
	}
}
