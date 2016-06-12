package ahmed_Deficated;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import main.Main;

public class Level {

	static int tileSize = 128;

	Main main;

	Image objects;
	int[][] objectTiles;
	Image floor;

	int gridWidth = 0;
	int gridHeight = 0;

	Player p;

	public Level(Main main) {
		this.main = main;
	}

	public void init() {

		int[][] mapGridRGB = null;

		try {
			BufferedImage gridImage = ImageLoader.objects;
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
		loadTiles(mapGridRGB);
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

		floor = new BufferedImage(gridWidth * tileSize, gridHeight * tileSize, BufferedImage.TYPE_INT_ARGB);
		Graphics g = floor.getGraphics();
		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {

				switch (mapGridRGB[j][i]) {
				case 0xFFA349A4:
					g.drawImage(Tile.sofa.image, i * tileSize, j * tileSize, tileSize,
							tileSize, null);
					break;
				case 0xFF000000:
					break;
				default:
					g.drawImage(Tile.floor.image, i * tileSize, j * tileSize, tileSize,
							tileSize, null);
					break;
				}

			}
		}

	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.drawImage(floor, 0, 0, Main.width, Main.height, (int) -Main.diffX, (int) Main.diffY,
				(int) (-Main.diffX + (Main.tilesW * tileSize)), (int) (Main.diffY + (Main.tilesH * tileSize)), null);
	}

}
