package vvv;

/**
 * Feladata, hogy létrehozza a bénulást okozó vírus hatását.
 */
public class NumbingAgent extends Agent {
	/** Feliratkozik a RoundTimeout-ra 3 kör erejéig. */
	public NumbingAgent() {
		super();
		RoundTimeout.getInstance().add(this, 3);
	}

	/**
	 * Felkeni a bénulást okozó vírust a target playerre és aktiválja a hatását
	 */
	public void activate(Player target) {
		NumbingAgentEffect ae = new NumbingAgentEffect();
		target.addAgentEffect(ae);
	}

	/** Visszaadja az ágens nevét. */
	@Override
	public String toString() {
		return "Bénító";
	}
}
