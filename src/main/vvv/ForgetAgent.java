package vvv;

/** A feladata, hogy létrehozza a genetikai kódokat elfelejtő vírus hatását. */
public class ForgetAgent extends Agent {
	/** Feliratkozik RoundTimeout-ra 3 körig. */
	public ForgetAgent() {
		super();
		RoundTimeout.getInstance().add(this, 3);
	}

	/**
	 * Felkeni a genetikai kódokat elfelejtő vírust a target playerre és aktiválja a
	 * hatását.
	 */
	public void activate(Player target) {
		ForgetAgentEffect ae = new ForgetAgentEffect();
		target.addAgentEffect(ae);
	}

	/** Visszaadja az ágens nevét. */
	@Override
	public String toString() {
		return "Felejtő";
	}
}
