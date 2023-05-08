package src.main.vvv;

import java.util.ArrayList;
import java.util.List;

/** A view értesítéséért felelős osztály. */
public class ModelPublisher {
	/** Egyetlen példány tárolásáért felelős statikus változó. */
	private static ModelPublisher modelPublisher = new ModelPublisher();

	/** A feliratkozott view-kat tárolja. */
	private List<ModelObserver> modelObservers = new ArrayList<>();

	/** Visszaadja az egyetlen példányt. */
	public static ModelPublisher getModelPublisher() {
		return modelPublisher;
	}

	/** Privát, hogy ne lehessen példányosítani. */
	private ModelPublisher() {
	}

	/** Feliratkoztat egy megfigyelőt. */
	public void subscribe(ModelObserver modelObserver) {
		modelObservers.add(modelObserver);
	}

	/** Leiratkoztat egy megfigyelőt. */
	public void unsubscribe(ModelObserver modelObserver) {
		modelObservers.remove(modelObserver);
	}

	/** Kihírdeti a győztest. */
	public void publishWin() {
		for (ModelObserver modelObserver : modelObservers) {
			modelObserver.onWin();
		}
	}

	/** Kihírdeti, hogy új játékos lép. */
	public void publishNextPlayer() {
		for (ModelObserver modelObserver : modelObservers) {
			modelObserver.onNextPlayer();
		}
	}

	/** Kihírdeti, hogy a játékos cselekvést hajthat végre. */
	public void publishTakeAction() {
		for (ModelObserver modelObserver : modelObservers) {
			modelObserver.onTakeAction();
		}
	}

	/** Kihírdeti, hogy cserére van szükség. */
	public void publishSwap(Equipment equipmentToSwap) {
		for (ModelObserver modelObserver : modelObservers) {
			modelObserver.onSwap(equipmentToSwap);
		}
	}

	/** Kihírdeti, hogy a játékos kimarad a körből. */
	public void publishSkip() {
		for (ModelObserver modelObserver : modelObservers) {
			modelObserver.onSkip();
		}
	}

}
