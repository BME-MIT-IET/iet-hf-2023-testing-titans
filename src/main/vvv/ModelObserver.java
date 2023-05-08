package src.main.vvv;

public interface ModelObserver {
	/**
	 * Azok az osztályok implementálják, amelyek szeretnének értesülni a
	 * győzelemről.
	 */
	default void onWin() {
	}

	/**
	 * Azok az osztályok implementálják, amelyek szeretnének értesülni arról, ha új
	 * játékos következik.
	 */
	default void onNextPlayer() {
	}

	/**
	 * Azok az osztályok implementálják, amelyek szeretnének értesülni a játékos
	 * által választott cselekvésekről.
	 */
	default void onTakeAction() {
	}

	/**
	 * Azok az osztályok implementálják, amelyek szeretnének értesülni akkor, ha az
	 * aktív játékos éppen cserélne.
	 */
	default void onSwap(Equipment equipmentToSwap) {
	}

	/**
	 * Azok az osztályok implementálják, amelyek szeretnének értesülni arról, ha a
	 * játékos nem tud lépni.
	 */
	default void onSkip() {
	}
}
