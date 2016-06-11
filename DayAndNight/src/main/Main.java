package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	public static void main(String args[]){
		Main main = new Main();
		main.run();
	}
	
	public void run(){
		initialise();
		while(true){
			update();
			draw();
		}
	}

	public void initialise(){
		this.setTitle("a game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);
	}
	
	public void update(){
		
	}
	
	public void draw(){
		Graphics g = this.getGraphics();
		Image offImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics offGraphics = offImage.getGraphics();
		g.drawImage(offImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
}
