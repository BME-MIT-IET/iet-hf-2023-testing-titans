package src.main.vvv;

/**
 * Ez egy absztrakt osztály, ami az ágensek viselkedését valósítja meg, melynek
 * általános feladata, hogy létrehozza az általa okozott hatást, valamint
 * levegye azt, ha lejárt a felhasználhatóságának kör ideje. Ismeri azon
 * tárolót, amelyben éppen megtalálható.
 */
public abstract class Agent implements Timeout {
	/** Azon virológus, akinek a tulajdonában van. */
	protected Player owner;

	/** owner-t átállítja */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Akkor hívódik meg, amikor lejár az Agent felhasználhatósága, ilyenkor
	 * törlődik a felhasználható ágensek listájából.
	 */
	public void timeout() {
		owner.removeAgent(this);
	}

	/** Felkeni az ágenst a target playerre és aktiválja a hatását. */
	public abstract void activate(Player target);

	/** Visszaadja az ágens nevét. */
	@Override
	public abstract String toString();
}
