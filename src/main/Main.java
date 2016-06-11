package main;

import java.awt.Graphics;
import java.awt.Image;

import utils.AbstractMain;
import utils.InputHandler;

public class Main extends AbstractMain {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		Main main = new Main();
		main.run();
	}

	public void initialise() {
		this.setTitle("Day And Night");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(InputHandler.screenSize);
		this.setVisible(true);
	}

	public void update() {
	}

	public void draw() {
		Graphics g = this.getGraphics();
		Image offImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics offGraphics = offImage.getGraphics();
		g.drawImage(offImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

}
