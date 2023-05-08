package src.main.vvv;

/**
 * Felelőssége, hogy amíg a virológust nem ütik meg baltával, véletlenszerű
 * lépkedésre legyen kényszerítve, eközben pedig minden raktár tartalmát
 * tönkreteszi, nem engedi a játékost semmilyen tevékenységet sem végrehajtani.
 */
public class BearEffect extends ChoreaAgentEffect {
	/** A konstruktorban feliratkozik a RoundTimeout-ra. */
	public BearEffect() {
		RoundTimeout.getInstance().remove(this);
	}

	/**
	 * Véletlenszerű lépésekre készteti a virológust.
	 */
	@Override
	public boolean handleTakeAction() {
		super.handleTakeAction();
		Field f = player.getCurrentField();
		f.addInfected(player);
		return true;
	}

	/**
	 * Lekezeli a raktárak által igényelt törlést.
	 * 
	 * @param sf az a raktár, amit ki kell üríteni
	 */
	@Override
	public boolean handleDestroying(StorageField sf) {
		sf.clearAll();
		return true;
	}

	/** Visszaadja a hatás nevét. */
	@Override
	public String toString() {
		return "Medvevírus";
	}
}
