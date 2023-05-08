package src.main.vvv;

/**
 * A feladata, hogy létrehozza a vitustánc vírus hatását.
 */
public class ChoreaAgent extends Agent {
	/** Feliratkozik a RoundTimeout-ra */
	public ChoreaAgent() {
		super();
		RoundTimeout.getInstance().add(this, 3);
	}

	/**
	 * Felkeni a vitustánc vírust a target playerre és aktiválja a hatását.
	 */
	public void activate(Player target) {
		ChoreaAgentEffect ae = new ChoreaAgentEffect();
		target.addAgentEffect(ae);
	}

	/** Visszaadja az ágens nevét. */
	@Override
	public String toString() {
		return "Vitustánc";
	}
}
