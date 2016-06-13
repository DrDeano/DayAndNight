package game2.src.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;

public class Enemy {

	private Point2D.Double location;
	private int health;
	private Image picture;
	
	
	public Enemy() {
		ResourceLoader.getImage("enemy.png");
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		g.drawImage(picture, (int)location.x, (int)location.y, null);
	}

}
