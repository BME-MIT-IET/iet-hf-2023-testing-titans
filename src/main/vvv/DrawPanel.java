package vvv;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Kirajzolja az aktuálisan lépő játékoshoz tartozó információkat.
 */
public class DrawPanel extends Canvas {
	/**
	 * A virológushoz tartozó karakter képét tárolja.
	 */
	private BufferedImage oneDocImg;
	/**
	 * Az egy mezőn megjelenő többi virológust jelképező kép.
	 */
	private BufferedImage doctorsImg;
	/**
	 * Megmondja, hogy van-e más virológus jelenleg lépőn kívül.
	 */
	private boolean moreVirologists = false;
	/**
	 * Megmondja, hogy jelenleg hány aktív játékos van.
	 */
	private String playerCount;
	/**
	 * A jelenleg kirajzolt játékos neve.
	 */
	private String playerName;
	/**
	 * A jelenleg lépő játékos mezője.
	 */
	private String playerCurrentField;
	/**
	 * A jelenleg lépő játékos által elérhető mezők listája.
	 */
	private List<String> neighborFields;
	/**
	 * A jelenleg kirajzolt virológus aminosav adatai.
	 */
	private String aminoStatus;
	/**
	 * A jelenleg kirajzolt virológus nukleotid adatai.
	 */
	private String nucleoStatus;
	/**
	 * A jelenleg kirajzolt virológus által megismert genetikai kódok.
	 */
	private String learnedGeneticCodes;
	/**
	 * A jelenleg kirjzolt virológus állapota, ha nincs hatás alatt, akkor Stabil,
	 * egyébként Fertőzött értékű.
	 */
	private String playerState;
	/**
	 * A jelenleg kirajzolt virológushoz tartozó effektek listája.
	 */
	private List<String> effects;
	/**
	 * A jelenleg kirajzolt virolúshoz tartozó ágensek listája.
	 */
	private List<String> agents;

	/**
	 * 
	 */
	public DrawPanel() {
		try {
			setBackground(Color.BLACK);
			oneDocImg = ImageIO.read(new File("./pics/oneDoc_150.png"));
			doctorsImg = ImageIO.read(new File("./pics/doctors_75.png"));

		} catch (IOException e) {
			System.out.println(System.getProperty("user.dir"));
			System.out.println("Sikertelen volt a képek betöltése.");
			e.printStackTrace();
		}
	}

	/**
	 * A referencia virológus és a játék állapota alapján újra rajzoltatja a
	 * felületet a repaint metódussal. Ezelőtt pedig minden adattagját a
	 * referenciaként kapott objektumok tulajdonságai alapján beállítja.
	 * 
	 * @param reference A virológus referenciája.
	 * @param game      A játék referenciája.
	 */
	public void redraw(Player reference, Game game) {
		playerCount = Integer.toString(game.getPlayers().size());
		playerName = reference.getName();
		playerCurrentField = reference.getCurrentField().toString();
		moreVirologists = reference.getCurrentField().getPlayers().size() > 1;
		neighborFields = new ArrayList<>();
		for (Field f : reference.getCurrentField().getNeighbors()) {
			neighborFields.add(f.toString());
		}
		aminoStatus = "Aminosav: " + Integer.toString(reference.getAminoAcidCount()) + "/"
				+ Integer.toString(reference.getMaxAminoAcidCount());
		nucleoStatus = "Nukleotid: " + Integer.toString(reference.getNucleotideCount()) + "/"
				+ Integer.toString(reference.getMaxNucleotideCount());
		List<String> geneticCodeNames = new ArrayList<>();
		for (GeneticCode g : reference.getGeneticCodes()) {
			geneticCodeNames.add(g.toString());
		}
		if (geneticCodeNames.isEmpty()) {
			learnedGeneticCodes = "-";
		} else {
			learnedGeneticCodes = String.join(", ", geneticCodeNames);
		}
		playerState = "Stabil";
		if (reference.getEffects().size() > reference.getEquipments().size())
			playerState = "Fertőzött";
		effects = new ArrayList<>();
		for (Effect e : reference.getEffects()) {
			effects.add(e.toString());
			if (effects.size() == 6) {
				effects.add("...");
				break;
			}
		}
		agents = new ArrayList<>();
		for (Agent a : reference.getAgents()) {
			agents.add(a.toString());
		}
		this.repaint();
	}

	/**
	 * Újra rajzolja a kijelző tartalmát a belső tagváltozóinak segítségével.
	 */
	public void paint(Graphics g) {
		if (playerName == null) {
			Font font = new Font("Calibri", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.WHITE);
			FontRenderContext frc = new FontRenderContext(null, true, true);
			Rectangle2D r2D = font.getStringBounds("A játék indításához kattints a megfelelő menüpontra.", frc);
			int rWidth = (int) Math.round(r2D.getWidth());
			int rX = (int) Math.round(r2D.getX());
			int wx = (this.getWidth() / 2) - (rWidth / 2) - rX;
			g.drawString("A játék indításához kattints a megfelelő menüpontra.", wx, this.getHeight() / 2);
			return;
		}
		g.setColor(Color.GREEN);
		g.fillOval(150, 150, 200, 200);
		g.drawImage(oneDocImg, 230, 150, null);
		if (moreVirologists)
			g.drawImage(doctorsImg, 160, 220, null);
		g.setColor(Color.white);
		Font font = new Font("Calibri", Font.BOLD, 20);
		g.setFont(font);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Rectangle2D r2D = font.getStringBounds(playerName, frc);
		int rWidth = (int) Math.round(r2D.getWidth());
		int rX = (int) Math.round(r2D.getX());
		int wx = (this.getWidth() / 2) - (rWidth / 2) - rX;
		g.drawString(playerName, wx, 50);
		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Élő virológusok:", 20, 100);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		g.drawString(playerCount, 125, 100);
		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Jelenlegi mező:", 20, 130);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		g.drawString(playerCurrentField, 20, 150);
		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Szomszédos mezők:", 20, 180);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		int n = 0;
		for (String fn : neighborFields) {
			g.drawString(fn, 20, 200 + n * 20);
			n++;
		}

		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Anyagmennyiség:", 20, 370);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		g.drawString(aminoStatus, 20, 400);
		g.drawString(nucleoStatus, 20, 420);

		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Megismert genetikai kódok:", 160, 370);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		g.drawString(learnedGeneticCodes, 160, 400);

		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Állapot:", 390, 150);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		g.drawString(playerState, 390, 170);

		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Effektek:", 390, 200);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		n = 0;
		for (String e : effects) {
			g.drawString(e, 390, 220 + n * 20);
			n++;
		}

		g.setFont(new Font("Calibri", Font.BOLD, 14));
		g.drawString("Ágensek:", 390, 370);
		g.setFont(new Font("Calibri", Font.PLAIN, 14));
		n = 0;
		for (String a : agents) {
			g.drawString(a, 390, 390 + n * 20);
			n++;
		}
	}

}
