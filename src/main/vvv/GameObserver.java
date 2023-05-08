package src.main.vvv;

/**
 * Eseménykezelő üres függvényeket tartalmaz, amelyeket az implementáló osztály
 * felülírhat. Ennek az interfésznek a feladata kijelölni, hogy milyen események
 * léteznek a játék során, amikre fel lehet iratkozni egy Game példányon.
 */
public class GameObserver {
	/**
	 * Új kör kezdete esetén értesít, azaz az utolsó játékos lépése után. Így az
	 * első kör kezdetéről nem lesz értesítés, csak a másodiktól kezdve.
	 */
	public void handleNewRound() {
	}
}
