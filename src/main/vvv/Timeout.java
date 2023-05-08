package src.main.vvv;

/**
 * Olyan interfész, amit azok az osztályok valósítanak meg, amiknek a példányaik
 * véges élettartammal rendelkeznek.
 */
public interface Timeout {
	/**
	 * Azt határozza meg, hogy a kör leteltével mi kell történjen az adott
	 * objektummal.
	 */
	public void timeout();
}
