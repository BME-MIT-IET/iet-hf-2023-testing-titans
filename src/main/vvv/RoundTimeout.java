package vvv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ez az osztály azokat az objektumokat menedzseli, amik megvalósítják a Timeout
 * interfészt, tehát azokat, amiknek véges az élettartama.
 */
public class RoundTimeout implements GameObserver {
	/** Az objektumokat rendeli össze az élettartamukkal. */
	private final Map<Timeout, Integer> timeouts = new HashMap<>();

	/** Az osztály egyetlen példányát tárolja. */
	private static final RoundTimeout roundTimeoutInstance = new RoundTimeout();

	/** Meghívja őse konstruktorát. */
	private RoundTimeout() {
		super();
	}

	/** Visszaadja az osztály egyetlen példányát. */
	public static RoundTimeout getInstance() {
		return roundTimeoutInstance;
	}

	/** Új játék körben csökkenti az objektumok élettartamát. */
	public void handleNewRound() {
		List<Timeout> removeableKeys = new ArrayList<>();
		for (Map.Entry<Timeout, Integer> entry : timeouts.entrySet()) {
			Timeout key = entry.getKey();
			entry.setValue(entry.getValue() - 1);
			if (entry.getValue() <= 0) {
				key.timeout();
				if (entry.getValue() <= 0)
					removeableKeys.add(key);
			}
		}
		for (Timeout key : removeableKeys) {
			timeouts.remove(key);
		}
	}

	/** Hozzáad egy objektumot az élettartamával a Map-hez. */
	public void add(Timeout timeout, int n) {
		timeouts.put(timeout, n);
	}

	/** Amikor lejár egy objektum élettartama, akkor kiveszi azt a Map-ből. */
	public void remove(Timeout timeout) {
		timeouts.remove(timeout);
	}

	/** Töröl minden beregisztrált Timeout-ot */
	public void clear() {
		timeouts.clear();
	}
}
