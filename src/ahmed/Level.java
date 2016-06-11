package ahmed;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Main;
import utils.ResourceLoader;

public class Level {

	static int tileSize = 16;

	Main main;

	int[][] objectTiles;
	int[][] floorTiles;

	int gridWidth = 0;
	int gridHeight = 0;

	public Level(Main main) {
		this.main=main;
	}

	public void init() {
		loadTiles();
		loadFloor();
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
//		handler.launcher.gameFrame.frame.createImage(width, height);
	}

	public void update() {

	}

	public void render(Graphics2D g2) {
//		renderFloor(g2);
		for (int j = 0; j < objectTiles.length; j++) {
			for (int i = 0; i < objectTiles[0].length; i++) {
				Tile.objectTiles.get(objectTiles[j][i]).render(g2, tileSize * i, tileSize * j);
				// System.out.print(
				// Tile.objectTiles.get(objectTiles[j][i]).id);
			}
			// System.out.println();
		}
	}
	
	private void renderFloor(Graphics2D g2){
		for (int j = 0; j < objectTiles.length; j++) {
			for (int i = 0; i < objectTiles[0].length; i++) {
//				floorTile.
				// System.out.print(
				// Tile.objectTiles.get(objectTiles[j][i]).id);
			}
			// System.out.println();
		}
	}

}
