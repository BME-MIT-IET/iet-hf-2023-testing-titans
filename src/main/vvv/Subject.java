package vvv;

/**
 * Olyan osztályoknak a függvényeit írja elő, melyek kepések arra, hogy
 * feliratkozzanak a rajtuk történő eseményekre. A lehetséges események esetén
 * hívódó függvényeket egy külön interfészbe kell kiszervezni, ez lesz a T
 * paraméter.
 */
public interface Subject<T> {
	/**
	 * Feliratkoztatja "t"-t a T-ben definiált eseményekre. A Subject interfacet
	 * megvalósító osztály feladata tárolni a feliratkozót.
	 */
	void subscribe(T t);

	/**
	 * Leiratkoztatja "t"-t az eseményekről. Szintén a Subjectet megvalósító osztály
	 * feladata.
	 */
	void unsubscribe(T t);
}
