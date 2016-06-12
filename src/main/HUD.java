package main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private Bar progress, sanity, fun, caffeine;
	
	public HUD(){
		int width = 2 * Main.width / 7;
		int height = Main.height / 20;
		progress = new Bar(Color.blue, 0, 100, 2000, 1, 20, 10, width, height);
		sanity = new Bar(Color.green, 100, 100, 800, -1, 20, 20 + height, width, height);
		fun = new Bar(Color.cyan, 100, 100, 800, -1, 20, 30 + 2 * height, width, height);
		caffeine = new Bar(Color.orange, 100, 100, 800, -1, 20, 40 + 3 * height, width, height);
	}
	
	public void update(){
		progress.update();
		sanity.update();
		fun.update();
		caffeine.update();
	}
	
	public void draw(Graphics g){
		progress.draw(g);
		sanity.draw(g);
		fun.draw(g);
		caffeine.draw(g);
	}

}
