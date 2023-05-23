package vvv;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapLoader {
	/**
	 * Egy adott játékmenethez tartozó konfigurációban megjelenő mező objektumok és
	 * a hozzájuk tartozó szöveges azonosító.
	 */
	private final Map<Field, String> fields = new HashMap<>();

	/**
	 * Visszaadja a létrehozott mezőket
	 */
	public List<Field> getFields() {
		return new ArrayList<>(fields.keySet());
	}

	/**
	 * A játékban a bemeneti nyelvben meghatározott azonosítóhoz tartozó genetikai
	 * kód referenciák vannak benne, a gyorsabb eléréshez.
	 */
	private static final Map<String, GeneticCode> geneticCodes = new HashMap<>();
	static {
			geneticCodes.put("prot", ProtectorGeneticCode.getInstance());
			geneticCodes.put("numb", NumbingGeneticCode.getInstance());
			geneticCodes.put("forg", ForgetGeneticCode.getInstance());
    	geneticCodes.put("chor", ChoreaGeneticCode.getInstace());
	}


	/**
	 * Az adott azonosítóhoz megkeresi a megfelelő mezőt.
	 * 
	 * @param id A mező azonosítója.
	 * @return A megtalált mező referenciája, ha nincs ilyen akkor null értékkel tér
	 *         vissza.
	 */
	public Field findField(String id) {
		for (Map.Entry<Field, String> entry : fields.entrySet()) {
			if (id.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Értelmezi a parancsokat és amennyiben helyesen adták meg őket, akkor
	 * végrehajtja a parancsot.
	 * 
	 * @param cmd A parancs szövege.
	 */
	public void interpretCommand(String cmd) {
		String[] elements = cmd.split(" ");
		for (int i = 0; i < elements.length; i++) {
			elements[i] = elements[i].split(",")[0];
		}
		if (elements.length == 4 && elements[0].equals("create") && elements[1].equals("field")) {
			createField(elements[2], elements[3]);
		} else if (elements.length == 3 && elements[0].equals("connect")) {
			connect(elements[1], elements[2]);
		} else if (elements.length == 5 && elements[0].equals("add") && elements[1].equals("equi")) {
			Field f = findField(elements[3]);
			addEquiField(f, elements[2]);
		} else if (elements.length == 4 && elements[0].equals("add") && elements[1].equals("gene")) {
			Field f = findField(elements[3]);
			addGeneField(f, elements[2]);
		}
	}

	/**
	 * Létrehozza a megfelelő típusú mezőt a megadott azonosítóval és hozzáadja azt
	 * a játékvezérlőhöz.
	 * 
	 * @param type A mező típusa.
	 * @param id   A mező azonosítója.
	 */
	public void createField(String type, String id) {
		Field field = null;
		switch (type) {
			case "labo":
				field = new LaboratoryField();
				break;
			case "free":
				field = new FreeField();
				break;
			case "shel":
				field = new ShelterField();
				break;
			case "stor":
				field = new StorageField();
				break;
			case "infe":
				field = new InfectiousLaboratoryField();
				break;
			default:
				break;
		}
		if (field != null) {
			field.setName(id);
			fields.put(field, id);
		}
	}

	/**
	 * Összekötet két megadott mezőt egymással.
	 * 
	 * @param fieldID1 Az első mező azonosítója.
	 * @param fieldID2 A második mező azonosítója.
	 */
	public void connect(String fieldID1, String fieldID2) {
		Field f1 = findField(fieldID1);
		Field f2 = findField(fieldID2);
		if (f1 == null || f2 == null || f1 == f2)
			return;
		f1.addNeighbor(f2);
		f2.addNeighbor(f1);
	}

	/**
	 * Létrehoz egy felszerelést az adott azonosítóval és típusból.
	 * 
	 * @param type A felszerelés típusa.
	 * @return A létrehozott felszerelés referenciája.
	 */
	private Equipment genEquiWithID(String type) {
		switch (type) {
			case "glov":
				return new WearGloveEquipment();
			case "prot":
				return new ProtectorEquipment();
			case "bag":
				return new BagEquipment();
			case "axe":
				return new AxeEquipment();
			default:
				return null;
		}
	}

	/**
	 * Hozzáadja az adott típusú és azonosítójú felszerelést a mezőhöz.
	 * 
	 * @param f    A mező referenciája.
	 * @param type A felszerelés típusa.
	 */
	public void addEquiField(Field f, String type) {
		Equipment e = genEquiWithID(type);
		if (e != null) {
			f.setEquipment(e);
		}
	}

	/**
	 * A megadott típusú genetikai kódot helyezi el a mezőn.
	 * 
	 * @param f    A mező referenciája.
	 * @param type A genetikai kód azonosítója.
	 */
	public void addGeneField(Field f, String type) {
		GeneticCode g = geneticCodes.get(type);
		if (g != null) {
			f.setGeneticCode(g);
		}
	}

	/**
	 * Az adott fájlból olvassa be és futtatja a nyelvtannak megfelelő utasításokból
	 * álló fájlt.
	 *
	 * @param fileName A konfigurációs fájl neve.
	 */
	public MapLoader(String fileName) {
		try (FileReader fs = new FileReader(new File(fileName));
				Scanner scan = new Scanner(fs)) {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				interpretCommand(line);
			}
		} catch (Exception e) {
			System.out.println("Can not open the file.");
		}
	}

	/** Ez a konstruktor csak a tesztek miatt lett létrehozva */
	public MapLoader() {
	}
}
