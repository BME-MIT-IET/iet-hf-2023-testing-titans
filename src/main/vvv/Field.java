package vvv;

import java.util.ArrayList;
import java.util.List;

/**
 * A játéktér mezőit megvalósító absztrakt osztály. Ismeri a rajta álló
 * virológusokat, valamint tárolja a szomszédos mezőket.
 */
public abstract class Field {
	/** A mezőn található játékosokat tárolja. */
	private final List<Player> players = new ArrayList<>();

	/** A mezőn található medve vírussal fertőzött játékosok. */
	private final List<Player> infected = new ArrayList<>();

	/** Szomszédos mezőket tárolja. */
	private final List<Field> neighbors = new ArrayList<>();

	/** A mező neve */
	private String name;

	/** Hozzáadja a virológust a mezőhöz, ezáltal átlépteti oda. */
	public void addPlayer(Player player) {
		players.add(player);
		if (!infected.isEmpty()) {
			BearEffect be = new BearEffect();
			player.infect(be);
			addInfected(player);
		}
	}

	/**
	 * Hozzáadja p-t az infected playerek listájához, illetve megfertőz minden olyan
	 * virológust, aki a mezőn áll és nem fertőzött.
	 */
	public void addInfected(Player p) {
		infected.add(p);
		for (Player other : players) {
			if (!infected.contains(other)) {
				other.infect(new BearEffect());
			}
		}
	}

	/** Eltávolítja a virológust az adott mezőről. */
	public void removePlayer(Player player) {
		players.remove(player);
		infected.remove(player);
	}

	/**
	 * Hozzáadja a következő szabad oldalához szomszédos mezőként az adott mezőt.
	 */
	public void addNeighbor(Field field) {
		neighbors.add(field);
	}

	/** Alapértelmezetten üres függvényként van implementálva. */
	public void collect(Player player) {
	}

	/** neighbors getter */
	public List<Field> getNeighbors() {
		return neighbors;
	}

	/** Semmit sem csinál */
	public void setEquipment(Equipment equipment) {
	}

	/** Semmit sem csinál */
	public void setGeneticCode(GeneticCode geneticCode) {
	}

	/** Semmit sem csinál */
	public void setInfection(Player p, List<Field> fields) {
	}

	/** Visszaadja a mező típusos nevét. Csak megjelenítéshez kell. */
	@Override
	public String toString() {
		return name;
	}

	/** Visszaadja a mezőn tartózkodó virológusokat. */
	public List<Player> getPlayers() {
		return players;
	}

	/** Beállítja a name fieldet */
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
