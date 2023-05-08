package src.main.vvv;

/** A védelmező ágenst reprezentálja. */
public class ProtectorAgent extends Agent {
	/** Feliratkozik a RoundTimeout-ra 3 kör erejéig. */
	public ProtectorAgent() {
		super();
		RoundTimeout.getInstance().add(this, 3);
	}

	/** Felkeni a védelmező ágenst a target playerre és aktiválja a hatását. */
	public void activate(Player target) {
		ProtectorAgentEffect ae = new ProtectorAgentEffect();
		target.addAgentEffect(ae);
	}

	/** Visszaadja az ágens nevét. */
	@Override
	public String toString() {
		return "Védelmező";
	}
}
