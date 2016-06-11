package ahmed;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Main;

public class Level {

	static int tileSize = 16;

	Main main;

	int[][] objectTiles;
	int[][] floorTiles;

	int gridWidth = 0;
	int gridHeight = 0;

	Player p;

	public Level(Main main) {
		this.main = main;
	}

	public void init() {
		loadTiles();
		loadFloor();
		p = new Player(main, 0, 0, 0);
	}

	private void loadTiles() {

		int[][] mapGridRGB = null;

		try {
			BufferedImage gridImage = ImageLoader.objects;
			gridWidth = gridImage.getWidth();
			gridHeight = gridImage.getHeight();
			System.out.println(gridWidth + "," + gridHeight);
			int[] mapArrayRGB = gridImage.getRGB(0, 0, gridWidth, gridHeight, null, 0, gridWidth);

			for (int i = 0; i < mapArrayRGB.length; i++) {
				System.out.println(mapArrayRGB[i]);
			}

			mapGridRGB = new int[gridHeight][gridWidth];
			for (int j = 0; j < gridHeight; j++) {
				for (int i = 0; i < gridWidth; i++) {
					mapGridRGB[j][i] = mapArrayRGB[i + j * gridWidth];
					System.out.println(mapGridRGB[j][i]);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Grid loaded");

		objectTiles = new int[gridHeight][gridWidth];

		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {
				for (Tile tile : Tile.objectTiles) {
					if (tile.tileColor == mapGridRGB[j][i]) {
						// System.out.println(tile.id);
						objectTiles[j][i] = tile.id;
						break;
					} else {
						System.out.println(tile.tileColor + "," + mapGridRGB[j][i]);
					}
				}
			}
		}
		System.out.println("Grid loaded2");

	}

	private void loadFloor() {
		// handler.launcher.gameFrame.frame.createImage(width, height);
	}

	public void update() {
		p.update();
	}

	public void render(Graphics2D g2) {
		// renderFloor(g2);
		for (int j = 0; j < objectTiles.length; j++) {
			for (int i = 0; i < objectTiles[0].length; i++) {
				Tile.objectTiles.get(objectTiles[j][i]).render(g2, tileSize * i, tileSize * j);
				// System.out.print(
				// Tile.objectTiles.get(objectTiles[j][i]).id);
			}
			// System.out.println();
		}
		p.render(g2);
	}

	private void renderFloor(Graphics2D g2) {
		for (int j = 0; j < objectTiles.length; j++) {
			for (int i = 0; i < objectTiles[0].length; i++) {
				// floorTile.
				// System.out.print(
				// Tile.objectTiles.get(objectTiles[j][i]).id);
			}
			// System.out.println();
		}

	}

	public Tile getTile(int x, int y) {
		// we have a 2d array of tile ids, with a given coord we fin the tile
		// id, and then find the tile based on the id from the Tile.objectTiles
		// list.
		return Tile.objectTiles.get(objectTiles[y][x]);
	}

}
