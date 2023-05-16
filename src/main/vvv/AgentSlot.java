package vvv;

import java.util.LinkedList;
import java.util.List;

/**
 * Menedzseli és tárolja a virológus által felhasználható ágenseket.
 */
public class AgentSlot extends Slot {
	/**
	 * Az megismert ágensek listája.
	 */
	private List<Agent> agents = new LinkedList<>();

	/**
	 * Létrehozza az ágens tárolót és 3-ra állítja a maximális méretét.
	 */
	public AgentSlot() {
		super(3);
	}

	/**
	 * Megadja, hogy összesen hány felhasználható ágens van a tárolóban.
	 */
	public int getValue() {
		return agents.size();
	}

	/**
	 * Megpróbálja az adott tárolóból az összes ágenst áthelyeztetni saját magába.
	 */
	public void fill(Slot fromSlot) {
		fromSlot.fillIt(this);
	}

	/**
	 * A paraméterül kapott aminosav tárolót megtölti a saját magában tárolt
	 * mennyiséggel, ameddig az meg nem telik teljesen, vagy a tárolóból el nem fogy
	 * az anyag.
	 */
	@Override
	public void fillIt(AgentSlot as) {
		while (as.agents.size() < maxValue && !agents.isEmpty()) {
			Agent a = agents.remove(0);
			a.setOwner(as.owner);
			as.agents.add(a);
		}
	}

	/**
	 * Hozzáadja az adott ágenst a tárolóhoz.
	 * 
	 * @param a A hozzáadandó ágens.
	 */
	public void addAgent(Agent a) {
		agents.add(a);
	}

	/**
	 * Lekezeli az ágenst eltávolító feladatot és eltávolítja az adott ágenst a
	 * tárolóból.
	 */
	@Override
	public void handleRemoveAgent(Agent a) {
		agents.remove(a);
	}

	/**
	 * A tárolóban lévő ágensek listáját adja vissza.
	 * 
	 * @return A ágensek listájának referenciája.
	 */
	@Override
	public List<Agent> getAgents() {
		return agents;
	}

	/**
	 * Visszadja igaz értékként, ha megtelt.
	 */
	@Override
	public boolean isFull() {
		return agents.size() == maxValue;
	}
}
