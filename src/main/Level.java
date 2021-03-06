package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Level {

	public static int tileSize = 16;

	Main main;

	Image objects;
	int[][] objectTiles;
	Image floor;

	int gridWidth = 0;
	int gridHeight = 0;

	Player p;

	public int getMapWidth() {
		return gridWidth * tileSize;
	}

	public int getMapHeight() {
		return gridHeight * tileSize;
	}

	public Level(Main main) {
		this.main = main;
	}

	public void init() {

		int[][] mapGridRGB = null;

		try {
			BufferedImage gridImage = ResourceLoader.getBufferedImage("objects.png");
			gridWidth = gridImage.getWidth();
			gridHeight = gridImage.getHeight();
			int[] mapArrayRGB = gridImage.getRGB(0, 0, gridWidth, gridHeight, null, 0, gridWidth);

			mapGridRGB = new int[gridHeight][gridWidth];
			for (int j = 0; j < gridHeight; j++) {
				for (int i = 0; i < gridWidth; i++) {
					mapGridRGB[j][i] = mapArrayRGB[i + j * gridWidth];
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
		// loadTiles(mapGridRGB);
		loadFloor(mapGridRGB);
	}

	private void loadTiles(int[][] mapGridRGB) {

		objects = new BufferedImage(gridWidth * tileSize, gridHeight * tileSize, BufferedImage.TYPE_INT_ARGB);
		Graphics g = objects.getGraphics();

		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {

				// switch (mapGridRGB[j][i]) {
				// case 0xFFffaec9:
				// g.drawImage(Tile.sofa.image, i * 16, j * 16, null);
				// objectTiles[i][j] = Tile.sofa.id;
				// break;
				// case 0xFFed1c24:
				// g.drawImage(Tile.computer.image, i * 16, j * 16, null);
				// objectTiles[i][j] = Tile.computer.id;
				// break;
				// case 0xFFB97A57:
				// g.drawImage(Tile.coffee.image, i * 16, j * 16, null);
				// objectTiles[i][j] = Tile.coffee.id;
				// break;
				// default:
				// break;
				// }

			}
		}

	}

	private void loadFloor(int[][] mapGridRGB) {

		floor = new BufferedImage(gridWidth * tileSize, gridHeight * tileSize, BufferedImage.TYPE_INT_RGB);
		Graphics g = floor.getGraphics();
		Image floor = ResourceLoader.getImage("carpet.png");
		Image walls = ResourceLoader.getImage("wallsprites.png");
		BufferedImage wall = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = wall.getGraphics();
		g2.drawImage(walls, 0, 0, 16, 16, 0, 0, 16, 16, null);
		g.setColor(Color.GREEN);

		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {
				switch (mapGridRGB[j][i]) {
				case 0xFFA349A4:
					g.drawImage(wall, i * tileSize, j * tileSize, tileSize, tileSize, null);
					break;
				case 0xFF000000:
					break;
				default:
					g.drawImage(floor, i * tileSize, j * tileSize, tileSize, tileSize, null);
					break;
				}
				if (i % 10 == 0 && j % 10 == 0) {
					g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
				}

			}
		}

	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(floor, 0, 0, Main.width, Main.height, (int) -Main.zeroXCoord, (int) -Main.zeroYCoord,
				(int) (Main.tilesW * tileSize - Main.zeroXCoord), (int) (Main.tilesH * tileSize - Main.zeroYCoord),
				null);
		g.drawRect( (int) Main.zeroXCoord / tileSize, (int) Main.zeroYCoord , (int) (10), (int) (10));
	}

}
