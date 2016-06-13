package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.sun.javafx.scene.control.skin.TitledPaneSkin;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static int FPS;
	private static float SPF;
	private static long currentTime, previousTime, deltaTime;

	public InputHandler input;
	public Level level;
	private HUD hud;
	public static int width, height;
	public static float ratio, tilesW, tilesH, widthpx, heightpx, zeroXCoord, zeroYCoord,
			root2 = (float) Math.sqrt(2.0);
	private static float deltas, speed = 80;

	public static void main(String args[]) {
		Main main = new Main();

		main.run();
	}

	public void run() {
		initialise();
		long minute = previousTime;
		int loops = 0;
		long time = previousTime + 1000000000l;
		while (running) {
			currentTime = System.nanoTime();
			deltaTime = currentTime - previousTime;
			deltas = ((float) deltaTime / 1000000000f);
			minute += deltaTime;
			loops++;
			if (minute >= time) {
				time += 1000000000l;
				loops = 0;
			}
			update(deltas);
			draw();
			previousTime = currentTime;
		}
	}

	public void initialise() {
		running = true;
		input = new InputHandler(this);
		previousTime = System.nanoTime();
		FPS = this.getGraphicsConfiguration().getDevice().getDisplayMode().getRefreshRate();
		SPF = 1f / FPS;
		this.setTitle("Day And Night");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(InputHandler.screenSize);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		width = this.getWidth();
		height = this.getHeight();
		hud = new HUD();
		ratio = (float) width / (float) height;
		int tiles = 16;
		if (height > width) {
			tilesH = tiles;
			tilesW = ratio * (float) tiles;
		} else {
			tilesW = tiles;
			tilesH = (float) tiles / ratio;
		}
		heightpx = (float) height / tilesH;
		widthpx = (float) width / tilesW;
		level = new Level(this);
		level.init();
		// Sound.dayNNight.loop();
	}

	// 0,0 top-left
	// left +
	// up +
	public void moveUp() {
		if (zeroYCoord < Level.tileSize * (tilesH/2)) {
			zeroYCoord += speed * deltas;
		}
	}

	public void moveDown() {
		if (zeroYCoord > -level.getMapHeight() + (Level.tileSize * (tilesH/2))) {
			zeroYCoord -= speed * deltas;
		}
	}

	public void moveRight() {
		if (zeroXCoord > -level.getMapWidth() + (Level.tileSize * (tilesW/2))) {
			zeroXCoord -= speed * deltas;
		}
	}

	public void moveLeft() {
		if (zeroXCoord < Level.tileSize * (tilesW/2)) {
			zeroXCoord += speed * deltas;
		}
	}

	public void moveUR() {
		if (zeroYCoord >= height) {
			moveRight();
		} else if (zeroXCoord <= -level.getMapWidth()) {
			moveUp();
		} else {
			zeroYCoord += (speed * deltas) / root2;
			zeroXCoord -= (speed * deltas) / root2;
		}
	}

	public void moveUL() {
		if (zeroYCoord >= height) {
			moveLeft();
		} else if (zeroXCoord >= 0) {
			moveUp();
		} else {
			zeroYCoord += (speed * deltas) / root2;
			zeroXCoord += (speed * deltas) / root2;
		}
	}

	public void moveDR() {
		if (zeroYCoord <= -level.getMapHeight()) {
			moveRight();
		} else if (zeroXCoord <= -level.getMapWidth()) {
			moveDown();
		} else {
			zeroYCoord -= (speed * deltas) / root2;
			zeroXCoord -= (speed * deltas) / root2;
		}
	}

	public void moveDL() {
		if (zeroYCoord <= -level.getMapHeight()) {
			moveLeft();
		} else if (zeroXCoord >= 0) {
			moveDown();
		} else {
			zeroYCoord -= (speed * deltas) / root2;
			zeroXCoord += (speed * deltas) / root2;
		}
	}

	private void update(float deltaTime) {

		System.out.println(zeroXCoord+","+zeroYCoord);
		if (input.isKeyDown(KeyEvent.VK_W)) {
			if (input.isKeyDown(KeyEvent.VK_D)) {
				moveUR();
			} else if (input.isKeyDown(KeyEvent.VK_A)) {
				moveUL();
			} else {
				moveUp();
			}
		} else if (input.isKeyDown(KeyEvent.VK_S)) {
			if (input.isKeyDown(KeyEvent.VK_D)) {
				moveDR();
			} else if (input.isKeyDown(KeyEvent.VK_A)) {
				moveDL();
			} else {
				moveDown();
			}
		} else if (input.isKeyDown(KeyEvent.VK_D)) {
			moveRight();
		} else if (input.isKeyDown(KeyEvent.VK_A)) {
			moveLeft();
		}

		if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		level.update();
		hud.update();
	}

	public void draw() {

		Graphics g = this.getGraphics();
		Image offImage = this.createImage(width, height);
		Graphics offGraphics = offImage.getGraphics();
		offGraphics.setColor(Color.black);
		offGraphics.fillRect(0, 0, width, height);

		level.draw(offGraphics);
		offGraphics.setColor(Color.red);
		offGraphics.fillRect((width / 2 - Level.tileSize / 2), (height / 2 - Level.tileSize / 2), Level.tileSize,
				Level.tileSize);
		hud.draw(offGraphics);
		g.drawImage(offImage, 0, 0, width, height, null);

	}

}
