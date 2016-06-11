package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

import ahmed.Launcher;
import ahmed.Level;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static int FPS;
	private static float SPF;
	private static long currentTime, previousTime, deltaTime;

	public InputHandler input;
	public Level level;

	public static void main(String args[]) {
		Main main = new Main();
		main.run();
	}

	public void run() {
		initialise();
		float deltas;
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
				System.out.println(loops);
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
		this.setVisible(true);
		level = new Level(this);
		level.init();
	}

	public void update(float deltaTime) {
		level.update();
	}

	public void draw() {

		Graphics g = this.getGraphics();
		Image offImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics offGraphics = offImage.getGraphics();
		level.render((Graphics2D) offGraphics);

		g.drawImage(offImage, 0, 0, this.getWidth(), this.getHeight(), null);

	}

}
