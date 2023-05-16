package vvv;

import java.util.List;
import java.util.LinkedList;

/**
 * Menedzseli és tárolja a virológus által birtokolt védőfelszereléseket.
 */
public class EquipmentSlot extends Slot {
	/**
	 * A tárolóban lévő védőfelszerelések összesége.
	 */
	private List<Equipment> equipments = new LinkedList<>();

	/**
	 * Létrehozz a tárolót és beálíltja a tároló maximális kapacitását 3-ra.
	 */
	public EquipmentSlot() {
		super(3);
	}

	/**
	 * Megadja, hogy összesen hány védőfelszerelés van a tárolóban.
	 */
	public int getValue() {
		return equipments.size();
	}

	/**
	 * Megpróbálja az adott tárolóból az összes felszerelést a saját maga maximális
	 * kapacitásáig áthelyezni.
	 */
	public void fill(Slot fromSlot) {
		fromSlot.fillIt(this);
	}

	/**
	 * A paraméterül kapott felszerelés tárolót megtölti a benne található
	 * felszerelésekkel és a saját magából pedig eltávolítja azokat.
	 */
	@Override
	public void fillIt(EquipmentSlot es) {
		while (es.equipments.size() < maxValue && !equipments.isEmpty()) {
			Equipment e = equipments.remove(0);
			if (owner != null) {
				owner.removeEffect(e);
			}
			es.addEquipment(e);
		}
	}

	/**
	 * Hozzáadja az adott felszerelést a tárolóhoz.
	 * 
	 * @param e A hozzáadandó felszerelés neve.
	 */
	public void addEquipment(Equipment e) {
		if (owner != null) {
			owner.addEffect(e);
		}
		equipments.add(e);
	}

	/**
	 * Eltávolítja az adott felszerelést a tárolóból.
	 */
	@Override
	public Equipment removeEquipment(int n) {
		Equipment removed = equipments.remove(n);
		if (owner != null) {
			owner.removeEffect(removed);
		}
		return removed;
	}

	/**
	 * Eltávolítja az adott felszerelést a tárolóból.
	 * 
	 * @param e Az eltávolítandó felszerelés referenciája.
	 */
	@Override
	public void removeEquipment(Equipment e) {
		equipments.remove(e);
		if (owner != null) {
			owner.removeEffect(e);
		}
	}

	/**
	 * Amennyiben megtelt a tároló felszereléssel igazzal tér vissza.
	 * 
	 * @return A felszerelés telítettség logikai értéke.
	 */
	public boolean isFull() {
		return equipments.size() == maxValue;
	}

	/**
	 * A tárolóban lévő első felszerelést adja vissza.
	 * 
	 * @return Az első felszerelés referenciája.
	 */
	@Override
	public Equipment getEquipment() {
		if (equipments.isEmpty())
			return null;
		return equipments.get(0);
	}

	/**
	 * A tárolóban lévő felszerelések listáját adja vissza.
	 * 
	 * @return A felszerelések listájának referenciája.
	 */
	@Override
	public List<Equipment> getEquipments() {
		return equipments;
	}
}
