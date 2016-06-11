package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Main extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static int FPS;
	private static float SPF;
	private static long currentTime, previousTime, deltaTime;

	public static void main(String args[]) {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		initialise();
		float deltas;
		long minute = 0;
		int loops = 0;
		while (running) {
			do{
				currentTime  = System.nanoTime();
				deltaTime = currentTime - previousTime;
				deltas = ((float)deltaTime/1000000000f);
			}while(deltas < SPF);
			minute+=deltaTime;
			loops++;
			if(minute>=1000000000){
				System.out.println(loops);
				loops = 0;
			}
			update(deltas);
			draw();
			previousTime = currentTime;
		}
	}

	public void initialise() {
		running = true;
		previousTime = System.nanoTime();
		FPS = this.getGraphicsConfiguration().getDevice().getDisplayMode().getRefreshRate();
		SPF = 1000000000f/FPS;
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
