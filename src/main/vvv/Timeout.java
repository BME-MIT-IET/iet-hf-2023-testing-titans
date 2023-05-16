package vvv;

/**
 * Olyan interfész, amit azok az osztályok valósítanak meg, amiknek a példányaik
 * véges élettartammal rendelkeznek.
 */
public interface Timeout {
	/**
	 * Azt határozza meg, hogy a kör leteltével mi kell történjen az adott
	 * objektummal.
	 */
	void timeout();
}
