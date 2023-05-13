package vvv;

import java.util.List;

/**
 * Ez az absztrakt osztály egy általános tárolót valósít meg.
 */
public abstract class Slot {
	/**
	 * A tároló maximális mérete.
	 */
	protected int maxValue;

	/**
	 * A tároló tulajdonosa alapértelmezetten null.
	 */
	protected Player owner = null;

	/**
	 * Létrehozza a tárolót, adott maximális kapacitással.
	 * 
	 * @param maxValue A maximális kapacitás értéke.
	 */
	public Slot(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * Ez egy absztrakt metódus, amelynek megvalósításával a leszármazottak a tárolt
	 * dolgok számát tudják visszaadni.
	 * 
	 * @return A tárolóban lévő elemek száma.
	 */
	public abstract int getValue();

	/**
	 * Ez egy absztrakt metódus, amelynek megvalósításával a leszármazottak
	 * telítettségüket
	 * tudják jelezni.
	 * 
	 * @return A telítettségi logikai értéke. Igaz, ha tele van.
	 */
	public abstract boolean isFull();

	/**
	 * Ez egy absztrakt metódus, amelynek megvalósításával a leszármazott a fromSlot
	 * tárolóból megpróbálja a lehető legtöbbet átrakatni az aktuális tárolóba.
	 * 
	 * @param fromSlot A tároló, amiből megpróbálja feltölteni a tartalmát,
	 *                 alapértelmezetten nem csinál semmit.
	 */
	public abstract void fill(Slot fromSlot);

	/**
	 * Alapértelmezetten semmit nem változtat, de a leszármazattok a maximális
	 * szintre növelésre használhatják.
	 */
	public void fillToMax() {
	}

	/**
	 * A paraméterül kapott ágens tárolót akarja megtölteni a saját tartalmával,
	 * alapértelmezetten nem tesz bele semmit.
	 * 
	 * @param as A tároló, amit meg kell tölteni.
	 */
	public void fillIt(AgentSlot as) {
	}

	/**
	 * A paraméterül kapott genetikai kód tárolót akarja megtölteni a saját
	 * tartalmával, alapértelmezetten nem tesz bele semmit.
	 * 
	 * @param gs A tároló, amit meg kell tölteni.
	 */
	public void fillIt(GeneticCodeSlot gs) {
	}

	/**
	 * A paraméterül kapott felszerelés tárolót akarja megtölteni a saját
	 * tartalmával, alapértelmezetten nem tesz bele semmit.
	 * 
	 * @param es A tároló, amit meg kell tölteni.
	 */
	public void fillIt(EquipmentSlot es) {
	}

	/**
	 * A paraméterül kapott aminosav tárolót akarja megtölteni a saját tartalmával,
	 * alapértelmezetten nem tesz bele semmit.
	 * 
	 * @param nucs A tároló, amit meg kell tölteni.
	 */
	public void fillIt(NucleotideSlot nucs) {
	}

	/**
	 * A paraméterül kapott aminosav tárolót akarja megtölteni a saját tartalmával,
	 * alapértelmezetten nem tesz bele semmit.
	 * 
	 * @param ams A tároló, amit meg kell tölteni.
	 */
	public void fillIt(AminoAcidSlot ams) {
	}

	/**
	 * Az ágens eltávolításokat lekezelő metódus, alapértelmezetten nem változtat
	 * semmin.
	 * 
	 * @param a A törlendő ágens.
	 */
	public void handleRemoveAgent(Agent a) {
	}

	/**
	 * A megismert genetikai kódokat törlését kezelő metódus, alapértelmezetten nem
	 * változtat semmin.
	 */
	public void handleClearMemory() {
	}

	/**
	 * Az anyagok megsemmisítését lekezelő metódus, ami alapértelmezetten nem
	 * változat semmin.
	 */
	public void handleClearMaterial() {
	}

	/**
	 * Visszaadja a tárolóban lévő animosav mennyiséget, ami az általános tároló
	 * viselkedésénél 0-át jelent.
	 * 
	 * @return A tárolóban lévő aminosav mennyiség, ami alapból minden tárolónál 0.
	 */
	public int getAminoAcidCount() {
		return 0;
	}

	/**
	 * Visszaadja a tárolóban lévő nukleotid mennyiséget, ami az általános tároló
	 * viselkedésénél 0-át jelent.
	 * 
	 * @return A tárolóban lévő nukleotid mennyiség, ami alapból minden tárolónál 0.
	 */
	public int getNucleotideCount() {
		return 0;
	}

	/**
	 * Az anyag átadásokat kezelő metódus, alapértelmezetten nem csinál semmit.
	 * 
	 * @param p A virológus, akinek át kell adni az anyagokat.
	 */
	public void handleMaterialGiving(Player p) {
	}

	/**
	 * A tárolóban lévő genetikai kódot adja vissza, de alapértelmezetten az
	 * általános viselkedésnél null-t jelent.
	 * 
	 * @return A genetikai kód referenciája, ami alapértelmezetten null.
	 */
	public GeneticCode getGeneticCode() {
		return null;
	}

	/**
	 * A tárolóban lévő genetikai kódok listáját adja vissza, de alapértelmezetten
	 * az általános viselkedésnél null-t jelent.
	 * 
	 * @return A genetikai kódok listájának referenciája, ami alapértelmezetten
	 *         null.
	 */
	public List<GeneticCode> getGeneticCodes() {
		return null;
	}

	/**
	 * A tárolóban lévő felszerelést adja vissza, de alapértelmezetten az
	 * általános viselkedésnél null-t jelent.
	 * 
	 * @return A felszerelés referenciája, ami alapértelmezetten null.
	 */
	public Equipment getEquipment() {
		return null;
	}

	/**
	 * A tárolóban lévő felszerelések listáját adja vissza, de alapértelmezetten
	 * az általános viselkedésnél null-t jelent.
	 * 
	 * @return A felszerelések listájának referenciája, ami alapértelmezetten
	 *         null.
	 */
	public List<Equipment> getEquipments() {
		return null;
	}

	/**
	 * Visszaadja a tárolóban tárolható maximális animosav mennyiséget, ami az
	 * általános tároló viselkedésénél 0-át jelent.
	 * 
	 * @return A tárolóban tárolható maximális aminosav mennyiség, ami alapból
	 *         minden tárolónál 0.
	 */
	public int getMaxAminoAcidCount() {
		return 0;
	}

	/**
	 * Visszaadja a tárolóban tárolható maximális nukleotid mennyiséget, ami az
	 * általános tároló viselkedésénél 0-át jelent.
	 * 
	 * @return A tárolóban tárolható maximális nukleotid mennyiség, ami alapból
	 *         minden tárolónál 0.
	 */
	public int getMaxNucleotideCount() {
		return 0;
	}

	/**
	 * A tárolóban lévő ágensek listáját adja vissza, de alapértelmezetten
	 * az általános viselkedésnél null-t jelent.
	 * 
	 * @return A ágensek listájának referenciája, ami alapértelmezetten
	 *         null.
	 */
	public List<Agent> getAgents() {
		return null;
	}

	/**
	 * Eltávolítja az adott felszerelést a tárolóból és visszaadja az eltávolított
	 * felszerelést, alapértelmezetten null-lal tér vissza.
	 * 
	 * @param n Az eltávolítandó felszerelés sorszáma.
	 * @return Az eltávolított eszköz referenciája, alapból null.
	 */
	public Equipment removeEquipment(int n) {
		return null;
	}

	/**
	 * Referencia szerint távolítja el a tárolóból a felszerelést.
	 * 
	 * @param e A felszerelés referenciája.
	 */
	public void removeEquipment(Equipment e) {
	}

	/**
	 * Beállítja a tároló tulajdonosát.
	 * 
	 * @param player A tároló tulajdonosa.
	 */
	public void setOwner(Player player) {
		owner = player;
	}
}
