package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Map {

	private Image background;
	private Enemy[] enemies;
	private Player player;
	private Rectangle[] hitboxes;
	
	private int numOfEnemies = 20;
	
	public Map() {
		init();
	}
	
	private void init(){
		
		player = new Player();
		
		enemies = new Enemy[numOfEnemies];
		for(int i = 0; i < numOfEnemies; i++){
			enemies[i] = new Enemy();
		}
		
		BufferedImage level = new BufferedImage(Main.width, Main.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = level.getGraphics();
		g.setColor(new Color(0xFFdd,0xFFa3,0xFF3d));
		
	}
	
	public void update(){
		for(Enemy enemy : enemies){
			enemy.update();
		}
		player.update();
	}
	
	public void draw(Graphics g){
		g.drawImage(background, 0, 0,Main.width, Main.height, null);
		for(Enemy enemy : enemies){
			enemy.draw(g);
		}
		player.draw(g);
	}

}
