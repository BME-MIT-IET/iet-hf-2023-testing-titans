package src.main.vvv;

import java.util.ArrayList;
import java.util.List;

/**
 * \A felelőssége, hogy kialakítsa az összefüggő játékteret, valamint elhelyezze
 * rajta a megfelelő mezőkön a speciális tárgyakat és a virológusokat. Ezen
 * felül felderíti, hogy az egyes játékosok mely mezőkön foglalnak jelenleg
 * helyet.
 */
public class MapManager {
	/** A játékteret alkotó mezők összesége. */
	private List<Field> fields = new ArrayList<>();

	/**
	 * Létrehozza az összefüggő pályát és elhelyezi a különböző helyszíneket és a
	 * hozzájuk tartozó speciális tárgyakat, illetve szétszórtan felhelyezi a
	 * virológusokat (players) is a játéktérre.
	 */
	public void generate() {
		fields = new MapLoader("./res/map.txt").getFields();
	}

	/** Visszaadja a játékban lévő mezők listáját. */
	public List<Field> getFields() {
		return fields;
	}
}
