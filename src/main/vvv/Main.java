package vvv;

/** A program indításáért felelős osztály. */
public class Main {

	/**
	 * A program belépési pontja. Létrehoz egy játék példányt és egy Controllert.
	 */
	public static void main(String[] args) {
		Game game = new Game();
		new Controller(game);
		game.subscribe(RoundTimeout.getInstance());
	}
}
