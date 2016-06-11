package ahmed;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import main.Main;

public class Level {

	static int tileSize = 16;

	Main main;

	Image objectTiles;
	Image floorTiles;

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
		loadTiles(mapGridRGB);
		loadFloor(mapGridRGB);
	}

	private void loadTiles(int[][] mapGridRGB) {
		
		objectTiles = new JFrame().createImage(gridWidth*16, gridHeight*16);
		Graphics g = objectTiles.getGraphics();
		System.out.println("Grid loaded");

		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {
				
				switch(mapGridRGB[j][i]){
				case 0xFFffaec9:
					g.drawImage(Tile.sofa.image, i*16, j*16, null);
					break;
				case 0xFFed1c24:
					g.drawImage(Tile.computer.image, i*16, j*16, null);
					break;
				case 0xFFB97A57:
					g.drawImage(Tile.coffee.image, i*16, j*16, null);
					break;
				default:
					break;	
				}
				
			}
		}

	}

	private void loadFloor(int[][] mapGridRGB) {

		floorTiles = new JFrame().createImage(gridWidth*16, gridHeight*16);
		Graphics g = floorTiles.getGraphics();
		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {
				
				switch(mapGridRGB[j][i]){
				case 0xFFA349A4:
					g.drawImage(Tile.sofa.image, i*16, j*16, null);
					break;
				case 0xFFFF7F27:
					g.drawImage(Tile.floor.image, i*16, j*16, null);
					break;
				default:
					break;	
				}
				
			}
		}
		
	}

	public void update() {
		p.update();
	}

	public void render(Graphics2D g2) {
		// renderFloor(g2);
//		for (int j = 0; j < objectTiles.length; j++) {
//			for (int i = 0; i < objectTiles[0].length; i++) {
//				Tile.objectTiles.get(objectTiles[j][i]).render(g2, tileSize * i, tileSize * j);
//				// System.out.print(
//				// Tile.objectTiles.get(objectTiles[j][i]).id);
//			}
//			// System.out.println();
//		}
//		p.render(g2);
	}

	private void renderFloor(Graphics2D g2) {
//		for (int j = 0; j < objectTiles.length; j++) {
//			for (int i = 0; i < objectTiles[0].length; i++) {
//				// floorTile.
//				// System.out.print(
//				// Tile.objectTiles.get(objectTiles[j][i]).id);
//			}
//			// System.out.println();
//		}

	}

	public Tile getTile(int x, int y) {
		// we have a 2d array of tile ids, with a given coord we fin the tile
		// id, and then find the tile based on the id from the Tile.objectTiles
		// list.
//		return Tile.objectTiles.get(objectTiles[y][x]);
		return null;
	}

}
