package vvv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** A virológust reprezentálja. */
public class Player {
	/**
	 * Ebben a tömbben tárolódnak azok az ágensek hatásai, amik éppen a játékoson
	 * vannak.
	 */
	private final List<Effect> effects = new ArrayList<>();

	/** A virológus neve. */
	private final String name;

	/**
	 * A virológus ebben az objektumban tárolja a nála lévő anyagokat, eszközöket,
	 * ágenseket, illetve a megismert genetikai kódokat.
	 */
	private final Inventory inventory;

	/** Jelenleg ezen a mezőn áll a virológus. */
	private Field currentField = null;

	/** Megadja, hogy a virológus meghalt-e. */
	private boolean dead = false;

	private Equipment equipmentToSwap;
	private final Random random = new Random();

	/** Virológus konstruktora. */
	public Player(String name) {
		super();

		inventory = new Inventory();

		AgentSlot as = new AgentSlot();
		as.setOwner(this);
		inventory.addSlot(as);

		AminoAcidSlot ams = new AminoAcidSlot(5);
		ams.setOwner(this);
		inventory.addSlot(ams);

		NucleotideSlot ns = new NucleotideSlot(5);
		ns.setOwner(this);
		inventory.addSlot(ns);

		EquipmentSlot es = new EquipmentSlot();
		es.setOwner(this);
		inventory.addSlot(es);

		GeneticCodeSlot gcs = new GeneticCodeSlot();
		gcs.setOwner(this);
		inventory.addSlot(gcs);

		this.name = name;
	}

	/**
	 * Egy ágenst hoz létre a paraméterként kapott genetikai kód alapján. Ezt az
	 * inventory attribútumában tárolja el, ha van benne még hely.
	 */
	public void createAgent(GeneticCode geneticCode) {
		geneticCode.createAgent(inventory);
	}

	/**
	 * A paraméterként kapott virológusra keni fel a paraméterként kapott ágenst.
	 */
	public void applyAgent(Agent agent, Player target) {
		target.anointedBy(this, agent);
		removeAgent(agent);
	}

	/**
	 * A paraméterként kapott virológus, a szintén hasonlóan megkapott ágenst kente
	 * fel a virológusra, aki erre tud reagálni.
	 */
	public void anointedBy(Player player, Agent agent) {
		boolean handled = false;
		for (int i = 0; i < effects.size(); i++) {
			Effect e = effects.get(i);
			if (e.handleAnointedBy(player, agent)) {
				handled = true;
				break;
			}
		}
		if (!handled) {
			agent.activate(this);
		}
	}

	/**
	 * A paraméterként kapott virológus, a szintén hasonlóan megkapott ágenst kente
	 * fel a virológusra, aki erre már nem tud reagálni.
	 */
	public void anointedByFinal(Agent agent) {
		agent.activate(this);
	}

	/** A paraméterként megkapott virológustól a lehető legtöbb anyagot lopja el. */
	public void stealMaterial(Player player) {
		player.materialRobbedBy(this);
	}

	/**
	 * A paraméterként megkapott virológustól véletlenszerűen lop egy
	 * védőfelszerelést.
	 */
	public void stealEquipment(Player player) {
		player.equipmentRobbedBy(this);
	}

	/** Determinisztikus lopást valósít meg. */
	public void stealEquipment(Player player, Equipment equipment) {
		player.equipmentRobbedBy(this, equipment);
	}

	/** A paraméterként megkapott virológus meglopja a virológus anyagkészletét. */
	public void materialRobbedBy(Player player) {
		boolean handled = true;
		for (Effect ef : effects) {
			if (!ef.handleStealing()) {
				handled = false;
			}
		}
		if (!handled) {
			inventory.giveMaterialTo(player);
		}
	}

	/**
	 * A paraméterként megkapott virológus meglopja a virológus
	 * felszereléskészletét.
	 */
	public void equipmentRobbedBy(Player player) {
		boolean handled = true;
		for (Effect ef : effects) {
			if (!ef.handleStealing()) {
				handled = false;
			}
		}
		if (!handled) {
			int n = inventory.getEquipments().size();
			int equipmentNumber = random.nextInt() % n;
			EquipmentSlot es = inventory.takeOutEquipment(equipmentNumber);
			player.fillAll(es);
		}
	}

	/**
	 * A paraméterként megkapott virológustól ellopja a paraméterül kapott
	 * felszerelést.
	 */
	public void equipmentRobbedBy(Player player, Equipment equipment) {
		boolean handled = true;
		for (Effect ef : effects) {
			if (!ef.handleStealing()) {
				handled = false;
				break;
			}
		}
		if (!handled) {
			inventory.removeEquipment(equipment);
			EquipmentSlot es = new EquipmentSlot();
			es.addEquipment(equipment);
			player.fillAll(es);
		}
	}

	/** A paraméterként megkapott mezőre átlép a virológus. */
	public void move(Field field) {
		if (currentField != null) {
			currentField.removePlayer(this);
		}
		field.addPlayer(this);
		field.collect(this);
		currentField = field;
	}

	/** Elhelyez a virológuson egy ágens hatását. */
	public void addAgentEffect(AgentEffect ae) {
		effects.add(ae);
		ae.onAttach(this);
	}

	/** Levesz egy ágens hatását a virológusról. */
	public void removeAgentEffect(AgentEffect ae) {
		effects.remove(ae);
		ae.onDetach();
	}

	/**
	 * Kicseréli az n sorszámú védőfelszerelést a szintén paraméterként kapott
	 * felszerelésre.
	 */
	public void swapEquipment(int n, Equipment e) {
		inventory.takeOutEquipment(n);
		EquipmentSlot es3 = new EquipmentSlot();
		es3.addEquipment(e);
		inventory.fillAll(es3);
	}

	/** Kicseréli az e1 felszerelést e2 felszerelésre. */
	public void swapEquipment(Equipment e1, Equipment e2) {
		inventory.removeEquipment(e1);
		EquipmentSlot es3 = new EquipmentSlot();
		es3.addEquipment(e2);
		inventory.fillAll(es3);
	}

	/** A paraméterül kapott tárolót hozzáadatja a készletéhez. */
	public void addSlot(Slot s) {
		s.setOwner(this);
		inventory.addSlot(s);
	}

	/** A paraméterül kapott tárolót törölteti a készletéből. */
	public void removeSlot(Slot s) {
		inventory.removeSlot(s);
	}

	/** Törölteti a készletéből a megismert genetikai kódokat. */
	public void clearGeneticCodes() {
		inventory.clearGeneticCodes();
	}

	/**
	 * A készlet minden tárolójába megpróbálja beletölteni a kapott paraméter
	 * tartalmát.
	 */
	public void fillAll(Slot s) {
		inventory.fillAll(s);
		if (s.getEquipment() != null) {
			equipmentToSwap = s.getEquipment();
			ModelPublisher.getModelPublisher().publishSwap(equipmentToSwap);
		}
	}

	/** A paraméterül kapott készletből áttölti a saját készletébe a tartalmát. */
	public void give(Inventory inventory) {
		this.inventory.fillFrom(inventory);
		if (inventory.getEquipment() != null) {
			equipmentToSwap = inventory.getEquipment();
			ModelPublisher.getModelPublisher().publishSwap(equipmentToSwap);
		}
	}

	/** Törölteti a virológus tárolójából az adott ágenst. */
	public void removeAgent(Agent a) {
		inventory.removeAgent(a);
	}

	/**
	 * Cselekvésre szólítja fel a virológust, amennyiben nincs semmilyen cselekvését
	 * befolyásoló hatás alatt.
	 * @return if the player can take action
	 */
	public boolean takeAction() {
		boolean canTakeAction = true;
		for (int i = 0; i < effects.size(); i++) {
			Effect e = effects.get(i);
			if (e.handleTakeAction()) {
				canTakeAction = false;
				break;
			}
		}
		return canTakeAction;
	}

	/** Hozzáad egy hatást a virolgósuhoz. */
	public void addEffect(Effect e) {
		effects.add(e);
		e.onAttach(this);
	}

	/** Leveszi a paraméterül kapott hatást a játékosról. */
	public void removeEffect(Effect e) {
		effects.remove(e);
		e.onDetach();
	}

	/** A virológus megszabadul a parméterül kapott felszereléstől. */
	public void throwAwayEquipment(Equipment e) {
		effects.remove(e);
		inventory.removeEquipment(e);
	}

	/** A mező tönkretételét kezeli. */
	public void destroyMe(StorageField sf) {
		for (Effect e : effects) {
			e.handleDestroying(sf);
		}
	}

	/** A paraméterül kapott játékost próbálja megölni. */
	public void kill(Player p) {
		for (Effect e : effects) {
			e.handleAttack(p);
		}
	}

	/** Kivezeti a virológust a játékból. */
	public void killedBy() {
		currentField.removePlayer(this);
		dead = true;
	}

	/** Megadja, hogy a játékos meghalt-e. */
	public boolean isDead() {
		return dead;
	}

	/** Medvevírussal fertőzi meg a virológust. */
	public void infect(BearEffect be) {
		boolean handled = false;
		for (Effect e : effects) {
			handled = e.handleInfection();
			if (handled) {
				break;
			}
			if (e.toString().equals(be.toString())) {
				return;
			}
		}
		if (!handled) {
			addEffect(be);
		}
	}

	/**
	 * Visszadaja annak a mezőnek az referenciáját, amin éppen tartózkodik a
	 * virológus.
	 */
	public Field getCurrentField() {
		return currentField;
	}

	/** Visszaadja a virológusra ható aktív hatásokat. */
	public List<Effect> getEffects() {
		return effects;
	}

	/** Visszaadja a virológusnál lévő aminosavak számát. */
	public int getAminoAcidCount() {
		return inventory.getAminoAcidCount();
	}

	/** Visszaadja a virológus aminosav kapacitását. */
	public int getMaxAminoAcidCount() {
		return inventory.getMaxAminoAcidCount();
	}

	/** Visszaadja a virológusnál lévő nukleotidok számát. */
	public int getNucleotideCount() {
		return inventory.getNucleotideCount();
	}

	/** Visszaadja a virológus nukleotid kapacitást. */
	public int getMaxNucleotideCount() {
		return inventory.getMaxNucleotideCount();
	}

	/** Visszaadja a virológusnál lévő ágensek listáját. */
	public List<Agent> getAgents() {
		return inventory.getAgents();
	}

	/** Beállítja a virológushoz a pillanatnyi mezőt, amin éppen áll */
	public void setField(Field currentField) {
		this.currentField = currentField;
	}

	/** Visszaadja a játékosnál lévő genetikai kódokat. */
	public List<GeneticCode> getGeneticCodes() {
		return inventory.getGeneticCodes();
	}

	/** Visszaadja a játékosnál lévő felszereléseket. */
	public List<Equipment> getEquipments() {
		return inventory.getEquipments();
	}

	/** Visszaadja a virológus nevét. */
	public String getName() {
		return name;
	}

	/** Visszaadja a virológus nevét. */
	@Override
	public String toString() {
		return getName();
	}

	/** Visszaadja a kicserélendő felszerelést. */
	public Equipment getEquipmentToSwap() {
		return equipmentToSwap;
	}
}
