package vvv;

/**
 * A játéktéren található óvóhely mezőket megvalósító osztály. Ezeken a mezőkön
 * találhatják meg a virológusok a védőfelszereléseket.
 */
public class ShelterField extends Field {
	/** Ebben tárolja el a védőfelszereléseket. */
	private final Inventory inventory = new Inventory();

	/**
	 * Meghívja őse konstruktorát. Elkészít egy tárolót, ami csak felszereléseket
	 * tud tárolni.
	 */
	public ShelterField() {
		super();
		inventory.addSlot(new EquipmentSlot());
	}

	/** Amikor ideér a játékos, odaadja neki a védőfelszerelést. */
	@Override
	public void collect(Player player) {
		player.give(inventory);
		Equipment remain = inventory.getEquipment();
		if (remain != null) {
			inventory.removeEquipment(remain);
		}
	}

	/** Beállítja az itt található eszközt a paraméterben kapottra */
	@Override
	public void setEquipment(Equipment e) {
		EquipmentSlot es = new EquipmentSlot();
		es.addEquipment(e);
		inventory.fillAll(es);
	}
}
