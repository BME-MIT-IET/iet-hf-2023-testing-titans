package vvv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fertőzött laborítóriumot reprezentáló osztály.
 */
public class InfectiousLaboratoryField extends LaboratoryField {

	/**
	 * A virológus és bejárandó útvonalak gyűjteménye.
	 */
	Map<Player, List<Field>> sets = new HashMap<>();

	/**
	 * Megismerteti a laboratórium mezőn lévő genetikai kóddal a virológust,
	 * valamint megpróbálja megfertőzni medvevírussal.
	 * 
	 */
	@Override
	public void collect(Player player) {
		super.collect(player);
		if (sets.keySet().contains(player)) {
			BearEffect be = new BearEffect();
			be.setFields(sets.get(player));
			player.infect(be);
		} else {
			player.infect(new BearEffect());
		}

	}

	/**
	 * Beállítja a fertőző laboratórium mezőn, hogy hogyan viselkedjen a medvevírus,
	 * amit a megadott rálépőre rárak.
	 * 
	 * @param p      A mezőre lépő referenciája.
	 * @param fields A mezők listája, amit be kell mindenképp járnia.
	 */
	@Override
	public void setInfection(Player p, List<Field> fields) {
		sets.put(p, fields);
	}
}