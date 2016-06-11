package ahmed;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import globalClasses.Pos;
import main.InputHandler;
import main.Main;

public class Player {
	InputHandler input;
	BufferedImage image;
	Pos pos;
	int id;
	Main main;

	double sanity = 1;
	double caffeine = 1;
	double fun = 1;
	double progress = 0;

	double x;
	double y;

	static final double baseSpeed = 5;

	double moveSpeed = baseSpeed;

	public Player(Main main, int id, double x, double y) {
		this.id = id;
		this.main = main;
		pos = new Pos(new Point2D.Double(x, y), 0);
		this.x = x;
		this.y = y;
		getSprite();
		input = main.input;
	}

	private void getSprite() {
		switch (id) {
		case 0:
			image = ImageLoader.character_black;
			break;
		case 1:
			image = ImageLoader.character_blue;
			break;
		case 2:
			image = ImageLoader.character_green;
			break;
		case 3:
			image = ImageLoader.character_purple;
			break;
		case 4:
			image = ImageLoader.character_yellow;
			break;
		case 5:
			image = ImageLoader.character_black;
			break;
		default:
			throw new RuntimeException("id cannot be greater than 5");
		}
	}

	public void update() {
		boolean left = input.isKeyDown(KeyEvent.VK_LEFT);
		boolean right = input.isKeyDown(KeyEvent.VK_LEFT);
		boolean up = input.isKeyDown(KeyEvent.VK_LEFT);
		boolean down = input.isKeyDown(KeyEvent.VK_LEFT);

		moveSpeed = sanity * baseSpeed;

		if (left) {
			x -= moveSpeed;
		} else if (right) {
			x += moveSpeed;
		}
		
		if (up) {
			y -= moveSpeed;
		} else if (down) {
			y += moveSpeed;
		}
		
	}

	public void render(Graphics2D g2) {
		int scale = Tile.scale;
		g2.drawImage(image,(int)x*scale, (int)y*scale, Tile.tileSize*Tile.scale, Tile.tileSize*Tile.scale, null);
	}
}
