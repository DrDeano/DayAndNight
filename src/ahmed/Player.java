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
Level level;
Camera cam;
	double sanity = 1;
	double caffeine = 1;
	double fun = 1;
	double progress = 0;

	double x;
	double y;

	int tileSize = Tile.tileSize;

	static final double baseSpeed = 2;

	double moveSpeed = baseSpeed;

	public Player(Main main, int id, double x, double y) {
		this.id = id;
		this.main = main;
		pos = new Pos(new Point2D.Double(x, y), 0);
		this.x = x;
		this.y = y;
		getSprite();
		input = main.input;
		level = main.level;
		cam = level.cam;
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
		boolean right = input.isKeyDown(KeyEvent.VK_RIGHT);
		boolean up = input.isKeyDown(KeyEvent.VK_UP);
		boolean down = input.isKeyDown(KeyEvent.VK_DOWN);

		moveSpeed = sanity * baseSpeed;

		double targetX = x;
		double targetY = y;

		if (up) {
			targetY -= moveSpeed;
		} else if (down) {
			targetY += moveSpeed;
		} else if (left) {
			targetX -= moveSpeed;
		} else if (right) {
			targetX += moveSpeed;
		}

		Tile nextTile = main.level.getTile((int) targetX/Tile.tileSize , (int) targetY/Tile.tileSize);
//		nextTile = null;
		if (nextTile == null) {
			updateX(targetX);
			updateY(targetY);
		} else if (!nextTile.solid) {
			updateX(targetX);
			updateY(targetY);
		}
	}

	public void updateX(double targetX) {
		x = targetX;
		// todo: send new x to Server
	}

	public void updateY(double targetY) {
		y = targetY;
		// todo: send new y to Server
	}

	public void render(Graphics2D g2) {
		g2.drawImage(image, (int) ((x )-cam.xOffset), (int)(( y )-cam.yOffset), tileSize , tileSize , null);
	}
}
