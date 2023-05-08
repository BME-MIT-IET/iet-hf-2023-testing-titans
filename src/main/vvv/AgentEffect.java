package src.main.vvv;

/**
 * Ez egy absztrakt osztály, ami az ágenshatások viselkedését valósítja meg.
 * Felelőssége, hogy kifejtse a felkent virológusra az ágens hatását mindaddig,
 * amíg érvényességének ideje le nem jár. Az érvényesség lejártáról tájékoztatja
 * az érvényességet figyelő osztály, hogy lekerüljön az aktív hatások
 * listájáról.
 */
public abstract class AgentEffect extends Effect implements Timeout {
	/**
	 * Akkor hívódik meg, amikor lejár az Effect időtartama, ilyenkor törlő- dik a
	 * virológusra ható effektek listájából.
	 */
	public void timeout() {
		if (player != null)
			player.removeAgentEffect(this);
	}
}
