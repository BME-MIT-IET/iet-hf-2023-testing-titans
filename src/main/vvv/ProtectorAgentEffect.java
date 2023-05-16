package vvv;

/** Ez az osztály a védelmező ágens hatását reprezentálja. */
public class ProtectorAgentEffect extends AgentEffect {
	/** Feliratkozik a RoundTimeout-ra 2 kör erejéig. */
	public ProtectorAgentEffect() {
		super();
		RoundTimeout roundTimeout = RoundTimeout.getInstance();
		roundTimeout.add(this, 2);
	}

	/** Mindig igazzal tér vissza, így kivédi a felkenést. */
	@Override
	public boolean handleAnointedBy(Player player, Agent agent) {
		return true;
	}

	/** Medvevírussal történő fertőzésre reagál. */
	@Override
	public boolean handleInfection() {
		return true;
	}

	/** Visszaadja a hatás nevét. */
	public String toString() {
		return "Védelmező";
	}
}
