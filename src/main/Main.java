package main;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import utils.InputHandler;

public class Main extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static int FPS;
	private static long currentTime, previousTime, deltaTime;

	public static void main(String args[]) {
		Main main = new Main();
		running = true;
		previousTime = System.nanoTime();
		main.run();
	}
	
	public void run() {
		initialise();
		int loops;
		while (running) {
			currentTime  = System.nanoTime();
			deltaTime = currentTime - previousTime;
			update(((float)deltaTime/1000000000f));
			draw();
		}
	}

	public void initialise() {
		FPS = this.getGraphicsConfiguration().getDevice().getDisplayMode().getRefreshRate();
		this.setTitle("Day And Night");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(InputHandler.screenSize);
		this.setVisible(true);
	}

	public void update(float deltaTime) {
		
	}

	public void draw() {
		Graphics g = this.getGraphics();
		Image offImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics offGraphics = offImage.getGraphics();
		g.drawImage(offImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

}
