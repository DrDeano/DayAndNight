package ahmed;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Main;

public class Camera {

	public double xOffset;
	public double yOffset;

	private int roomWidth;
	private int roomHeight;

	private int centerX;
	private int centerY;

	Main main;
	Level level;

	public Camera(Main main, Level level) {
		this.main = main;
		this.level = level;
		roomWidth = level.getLevelWidth();
		roomHeight = level.getLevelHeight();

		centerX = Main.width / 2;
		centerY = Main.height / 2;
	}

	private void reposition() {
		xOffset = Math.max(0, xOffset);
		xOffset = Math.min((roomWidth * Tile.tileSize) -  Main.width, xOffset);
		yOffset = Math.max(0, yOffset);
		yOffset = Math.min((roomHeight * Tile.tileSize) - Main.height, yOffset);
	}

	public void centerOnPlayer() {
		double nxOffset = level.p.x -  Main.width / 2;
		double nyOffset = level.p.y - Main.height / 2;
		if (xOffset != nxOffset) {
			xOffset += (nxOffset - xOffset) * 0.1;
		}
		if (yOffset != nyOffset) {
			yOffset += (nyOffset - yOffset) * 0.1;
		}
//		xOffset = (level.p.x) - mainWidth / 2;
//		yOffset = (level.p.y) - mainHeight / 2;
		System.out.print(xOffset + ", after");

		// reposition();
		System.out.println(xOffset);

		// System.out.println(level.p.x+","+mainWidth/2);
	}

	public void update() {
		centerOnPlayer();
	}

	public void move(double xAmt, double yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		reposition();
	}

	public int xOTiles() {
		return (int) (xOffset / Tile.tileSize);
	}

	public int yOTiles() {
		return (int) (yOffset / Tile.tileSize);
	}

	public void render(Graphics2D g2, BufferedImage texture, double x, double y, int width, int height) {
		g2.drawImage(texture, (int) (x - xOffset), (int) (y - yOffset), width, height, null);
	}

}
