package vvv;

import java.util.Random;

/**
 * Ez az osztály reprezentálja a köpenyt, amely 82,3% valószínűséggel védi ki az
 * ezt viselő virológusra felkent ágenseket.
 */
public class ProtectorEquipment extends Equipment {
	/** A determinisztikusságot lehet vele beállítani. */
	private boolean isDeterm = false;
	/**
	 * Amennyiben determinisztikusan működik, akkor azt szabályozza, hogy mindent ki
	 * tud-e védeni vagy semmit sem.
	 */
	private boolean canProtect = true;

	private static final Random random = new Random();
	/**
	 * Megkapja paraméterként, hogy melyik virológus kent rá mit, majd megpróbálja
	 * kivédeni a felkenést.
	 */
	@Override
	public boolean handleAnointedBy(Player player, Agent agent) {
		if (!isDeterm) {
			float randomValue = random.nextFloat();
			return randomValue < 0.823;
		} else {
			return canProtect;
		}
	}

	/** Beállítja a determinisztikusságát az eszköznek. */
	@Override
	public void setDeterm(boolean isDeterm, boolean canProtect) {
		this.isDeterm = isDeterm;
		this.canProtect = canProtect;
	}

	/** A medvevírussal történő fertőzésre reagál. */
	@Override
	public boolean handleInfection() {
		if (!isDeterm) {
			float randomValue = random.nextFloat();
			return randomValue < 0.823;
		} else {
			return canProtect;
		}
	}

	/** Visszaadja a felszerelés nevét. */
	@Override
	public String toString() {
		return "Köpeny";
	}
}
