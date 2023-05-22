package vvv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Menedzseli a virológusnál lévő tárolókat, melyeket hozzá tudja adni vagy
 * éppen el tudja távolítani azokat a készletből. Továbbá képes egy tároló
 * tartalmát a meglévő tárolóiba beletölteni.
 */
public class Inventory {
	/**
	 * A készletében lévő tárolók összesége, amelyekben különböző anyagokat, illetve
	 * felszereléseket tárol.
	 */
	private final List<Slot> slots = new ArrayList<>();

	/**
	 * Hozzáadja az adott tárolót a virológus készletéhez.
	 * 
	 * @param s A hozzáadandó tároló referenciája.
	 */
	public void addSlot(Slot s) {
		slots.add(s);
	}

	/**
	 * Eltávolítja az adott tárolót a virológus készletéből.
	 * 
	 * @param s Az eltávolítandó tároló referenciája.
	 */
	public void removeSlot(Slot s) {
		slots.remove(s);
	}

	/**
	 * Az adott tároló tartalmát megpróbálja a saját készletében lévő tárolókba
	 * beletölteni, azoknak a maximális kapacitásáig.
	 * 
	 * @param s A tárolóiba beletöltendő tároló.
	 */
	public void fillAll(Slot s) {
		for (Slot slot : slots) {
			slot.fill(s);
		}
	}

	/**
	 * A maximális szintig tölti a tárolóit.
	 */
	public void fillToMax() {
		for (Slot s : slots) {
			s.fillToMax();
		}
	}

	/**
	 * A paraméterként megkapott Inventory példányból mindent megpróbál átmozgatni a
	 * jelenlegi Inventory példányba.
	 * 
	 * @param fromInv Ahonnan átmozgatja a tárolók tartalmát.
	 */
	public void fillFrom(Inventory fromInv) {
		int slotSize = slots.size();
		for (Slot ss : fromInv.slots) {
			for (int i = 0; i < slotSize; i++) {
				slots.get(i).fill(ss);
			}
		}
	}

	/**
	 * Ellenőrzi, hogy megtudja-e a tölteni a paraméterül kapott készlet minden
	 * tárolóját maximum szintre, ha nem akkor pedig visszatölteni az áttöltötted. A
	 * sikeres áttöltés után igazzal, egyébként hamissal tér vissza.
	 * 
	 * @param inventory Ezen tárolót akarja megtölteni.
	 * @return Az áttöltés sikeressége.
	 */
	public boolean checkMaterial(Inventory inventory) {
		inventory.fillFrom(this);
		if (!inventory.isFull()) {
			for (Slot s : inventory.slots) {
				fillAll(s);
			}
			return false;
		}
		return true;
	}

	/**
	 * Amennyiben minden tárolója megtelt igazzal tér vissza.
	 * 
	 * @return Az össz-telítettség logikai értéke.
	 */
	public boolean isFull() {
		for (Slot s : slots) {
			if (!s.isFull())
				return false;
		}
		return true;
	}

	/**
	 * A paraméterül kapott virológusnak áttölti a nála lévő anyagmennyiségeket.
	 * 
	 * @param player A virológus, akinek áttölti a nála lévő anyagmennyiséget.
	 */
	public void giveMaterialTo(Player player) {
		for (Slot s : slots) {
			s.handleMaterialGiving(player);
		}
	}

	/**
	 * Az adott sorszámú felszerelést eltávolítja a tárolóból, majd visszaad egy új
	 * tárolót, amiben az eltávolított felszerelés található meg.
	 * 
	 * @param n Az eltávolítandó felszerelés sorszáma.
	 * @return Az eltávolított felszerelés tartalmazó tároló.
	 */
	public EquipmentSlot takeOutEquipment(int n) {
		EquipmentSlot es2 = new EquipmentSlot();
		for (int i = 0; i < slots.size(); i++) {
			Equipment e = slots.get(i).removeEquipment(n);
			if (e != null) {
				es2.addEquipment(e);
				break;
			}
		}
		return es2;
	}

	/**
	 * Törölteti az adott felszerelést a tárolóiból.
	 * 
	 * @param e A törlendő felszerelés referenciája.
	 */
	public void removeEquipment(Equipment e) {
		for (int i = 0; i < slots.size(); i++) {
			slots.get(i).removeEquipment(e);
		}
	}

	/**
	 * A paraméterként kapott ágenst kiveszi a tárolóból.
	 * 
	 * @param a Az eltávolítandó ágens referenciája.
	 */
	public void removeAgent(Agent a) {
		for (Slot s : slots) {
			s.handleRemoveAgent(a);
		}
	}

	/**
	 * Törölteti a tárolóiból az összes megismert genetikai kód referenciáját.
	 */
	public void clearGeneticCodes() {
		for (Slot s : slots) {
			s.handleClearMemory();
		}
	}

	/**
	 * Az anyagtárolóiból törölteti az anyagokat.
	 */
	public void clearMaterials() {
		for (Slot s : slots) {
			s.handleClearMaterial();
		}
	}

	/**
	 * Visszaadja az első genetikai kód tároló tartalmát, amiben van genetikai kód.
	 * 
	 * @return Az első genetikai kód referenciája, alapértélmezetten null.
	 */
	public GeneticCode getGeneticCode() {
		for (Slot s : slots) {
			GeneticCode g = s.getGeneticCode();
			if (g != null) {
				return g;
			}
		}
		return null;
	}

	/**
	 * Visszaadja a tárolóiban tárolt összes genetikai kód listáját.
	 * 
	 * @return A genetikai kódok listája, alapértelmezetten null.
	 */
	public List<GeneticCode> getGeneticCodes() {
		for (Slot s : slots) {
			List<GeneticCode> g = s.getGeneticCodes();
			if (g != null) {
				return g;
			}
		}
		return Collections.emptyList();
	}

	/**
	 * Visszaadja az első felszerlsé tároló tartalmát, amiben van felszerelés.
	 * 
	 * @return Az első felszerelés referenciája, alapértélmezetten null.
	 */
	public Equipment getEquipment() {
		for (Slot s : slots) {
			Equipment e = s.getEquipment();
			if (e != null) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Visszaadja a tárolóiban tárolt összes felszerelés listáját.
	 * 
	 * @return A felszerelések listája, alapértelmezetten null.
	 */
	public List<Equipment> getEquipments() {
		for (Slot s : slots) {
			List<Equipment> e = s.getEquipments();
			if (!e.isEmpty()) {
				return e;
			}
		}
		return Collections.emptyList();
	}

	/**
	 * Összegzi a tárolóiban lévő aminosav mennyiséget.
	 * 
	 * @return A tárolóiban lévő amninosav mennyiség összesége.
	 */
	public int getAminoAcidCount() {
		int sum = 0;
		for (Slot s : slots) {
			sum += s.getAminoAcidCount();
		}
		return sum;
	}

	/**
	 * Összegzi a tárolóiban lévő nukleotid mennyiséget.
	 * 
	 * @return A tárolóiban lévő nukleotid mennyiség összesége.
	 */
	public int getNucleotideCount() {
		int sum = 0;
		for (Slot s : slots) {
			sum += s.getNucleotideCount();
		}
		return sum;
	}

	/**
	 * A tárolóban lévő ágensek listáját adja vissza.
	 * 
	 * @return A ágensek listájának referenciája, ami alapértelmezetten
	 *         null.
	 */
	public List<Agent> getAgents() {
		for (Slot s : slots) {
			List<Agent> a = s.getAgents();
			if (a != null) {
				return a;
			}
		}
		return Collections.emptyList();
	}

	/**
	 * Megadja, hogy az adott tárolóba mennyi aminosav fér el legfeljebb.
	 * 
	 * @return a maximális aminosav mennyiség
	 */
	public int getMaxAminoAcidCount() {
		int sum = 0;
		for (Slot s : slots) {
			sum += s.getMaxAminoAcidCount();
		}
		return sum;
	}

	/**
	 * Megadja, hogy az adott tárolóba mennyi nukleotid fér el legfeljebb.
	 * 
	 * @return a maximális nukleotid mennyiség
	 */
	public int getMaxNucleotideCount() {
		int sum = 0;
		for (Slot s : slots) {
			sum += s.getMaxNucleotideCount();
		}
		return sum;
	}
}
