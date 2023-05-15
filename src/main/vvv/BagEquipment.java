package vvv;

import java.util.ArrayList;
import java.util.List;

/**
 * Felelősége, hogy menedzselje a benne található tárolók hozzáadását és
 * leválasztását a tulajdonosának készletének esetében, továbbá a leválasztáskor
 * a zsákban maradó anyagoktól megszabadul.
 */
public class BagEquipment extends Equipment {
	/** A zsák által adott plusz anyag tárolók összesége. */
	private List<Slot> slots = new ArrayList<>();

	/** Létrehozza a táska tartalmát és feltölti azt azzal. */
	public BagEquipment() {
		AminoAcidSlot ams = new AminoAcidSlot(2);
		NucleotideSlot nucs = new NucleotideSlot(2);
		slots.add(ams);
		slots.add(nucs);
	}

	/**
	 * Hozzáadja az általa nyújtott tárolókat a tulajdonosának készletéhez.
	 */
	public void onAttach(Player player) {
		this.player = player;
		for (Slot s : slots) {
			player.addSlot(s);
		}
	}

	/**
	 * Leválasztja az általa nyújtott tárolókat a korábbi tulajdonosának
	 * készletéből, illetve eldobja a tárolókban maradó anyagokat.
	 */
	public void onDetach() {
		if (player == null) {
			return;
		}
		for (Slot s : slots) {
			player.removeSlot(s);
		}
		player = null;
	}

	/** Visszaadja a felszerelés nevét. */
	@Override
	public String toString() {
		return "Zsák";
	}
}
