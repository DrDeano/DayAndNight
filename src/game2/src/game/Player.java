package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

public class Player {

	public static Point2D.Double location;
	private int health;
	private float speed = 10;

	public Player() {

		ResourceLoader.getImage("player.png");
	}

	// 0,0 top-left
	// left +
	// up +
	public Point2D.Double getLocation() {
		return location;
	}

	public void setLocation(Point2D.Double location) {
		this.location = location;
	}

	public void moveUp() {
		if () {
			location.y += speed;
		}
	}

	public void moveDown() {
		if () {
			location.y -= speed;
		}
	}

	public void moveRight() {
		if () {
			location.x -= speed;
		}
	}

	public void moveLeft() {
		if () {
			location.x += speed;
		}
	}

	public void moveUR() {
		if () {
			moveRight();
		} else if () {
			moveUp();
		} else {
			location.x += (speed) / Main.root2;
			location.y -= (speed) / Main.root2;
		}
	}

	public void moveUL() {
		if () {
			moveLeft();
		} else if () {
			moveUp();
		} else {
			location.x += (speed) / Main.root2;
			location.y += (speed) / Main.root2;
		}
	}

	public void moveDR() {
		if () {
			moveRight();
		} else if () {
			moveDown();
		} else {
			location.x -= (speed) / Main.root2;
			location.y -= (speed) / Main.root2;
		}
	}

	public void moveDL() {
		if () {
			moveLeft();
		} else if () {
			moveDown();
		} else {
			location.x -= (speed) / Main.root2;
			location.y += (speed) / Main.root2;
		}
	}

	public void update() {

		if (Main.input.isKeyDown(KeyEvent.VK_W)) {
			if (Main.input.isKeyDown(KeyEvent.VK_D)) {
				moveUR();
			} else if (Main.input.isKeyDown(KeyEvent.VK_A)) {
				moveUL();
			} else {
				moveUp();
			}
		} else if (Main.input.isKeyDown(KeyEvent.VK_S)) {
			if (Main.input.isKeyDown(KeyEvent.VK_D)) {
				moveDR();
			} else if (Main.input.isKeyDown(KeyEvent.VK_A)) {
				moveDL();
			} else {
				moveDown();
			}
		} else if (Main.input.isKeyDown(KeyEvent.VK_D)) {
			moveRight();
		} else if (Main.input.isKeyDown(KeyEvent.VK_A)) {
			moveLeft();
		}

	}

	public void draw(Graphics g) {

		g.drawImage(picture, (int) location.x, (int) location.y, null);
	}

}
