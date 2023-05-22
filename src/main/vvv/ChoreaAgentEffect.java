package vvv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Felelőssége, hogy hatása lejártáig véletlenszerű lépkedésekbe kényszerítse
 * azt a virológust, akire éppen kifejti a hatását, eközben nem enged meg más
 * tevékenységeket végrehajtania.
 */
public class ChoreaAgentEffect extends AgentEffect {
	/**
	 * azon mezők listája, amelyeket a hatása lejárta során végig fog járni a
	 * megadás sorrendjében. A determinisztikus működés szabályozásához szükséges.
	 * Alapértelmezetten ez egy üres lista.
	 */
	private List<Field> fields = new ArrayList<>();
	private static final Random random = new Random();

	/** Feliratkozik a RoundTimeout-ra 3 körig. */
	public ChoreaAgentEffect() {
		super();
		RoundTimeout.getInstance().add(this, 3);
	}

	/**
	 * Igaz értékkel tér vissza és közben egy véletlenszerű mezőre lépteti a
	 * virológust.
	 */
	@Override
	public boolean handleTakeAction() {
		if (!fields.isEmpty()) {
			player.move(fields.get(0));
			fields.remove(0);
		} else {
			Field field = player.getCurrentField();
			List<Field> neighborFields = field.getNeighbors();
			int index = random.nextInt(neighborFields.size());
			Field nextField = neighborFields.get(index);
			player.move(nextField);
		}
		return true;
	}

	/**
	 * A paraméterül megkapott mező lista alapján fogja kiválasztani, hogy mely
	 * mezőkre fog lépni a handleTakeAction metódus során.
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/** Visszaadja a hatás nevét. */
	@Override
	public String toString() {
		return "Vitustánc";
	}
}
