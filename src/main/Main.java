package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import ahmed_Deficated.Level;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static int FPS;
	private static float SPF;
	private static long currentTime, previousTime, deltaTime;

	public InputHandler input;
	public Level level;
	private HUD hud;
	public static int width,height;
	public static float ratio, tilesW, tilesH, widthpx, heightpx, diffX, diffY;
	private static float deltas, moveSpeed = 80;

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
		ratio = (float)width / (float)height;
		int tiles = 16;
		if(height > width){
			tilesH = tiles;
			tilesW = ratio * (float)tiles;
		}else{
			tilesW = tiles;
			tilesH = (float)tiles / ratio;
		}
		heightpx = (float)height / tilesH;
		widthpx = (float)width / tilesW;
		level = new Level(this);
		level.init();
		//Sound.dayNNight.loop();
	}

	public void update(float deltaTime) {
		if(input.isKeyDown(KeyEvent.VK_W) && input.isKeyDown(KeyEvent.VK_A)){
			
		}else if(input.isKeyDown(KeyEvent.VK_W) && input.isKeyDown(KeyEvent.VK_D)){
			
		}else if(input.isKeyDown(KeyEvent.VK_S) && input.isKeyDown(KeyEvent.VK_A)){
			
		}else if(input.isKeyDown(KeyEvent.VK_S) && input.isKeyDown(KeyEvent.VK_D)){
			
		}else if(input.isKeyDown(KeyEvent.VK_W)){
			diffY -= moveSpeed * deltas;
		}else if(input.isKeyDown(KeyEvent.VK_S)){
			diffY += moveSpeed * deltas;
		}else if(input.isKeyDown(KeyEvent.VK_A)){
			diffX += moveSpeed * deltas;
		}else if(input.isKeyDown(KeyEvent.VK_D)){
			diffX -= moveSpeed * deltas;
		}else{
			
		}
		level.update();
		hud.update();
	}

	public void draw() {

		Graphics g = this.getGraphics();
		Image offImage = this.createImage(width, height);
		Graphics offGraphics = offImage.getGraphics();
		level.draw(offGraphics);
		hud.draw(offGraphics);
		g.drawImage(offImage, 0, 0, this.getWidth(), this.getHeight(), null);

	}

}
