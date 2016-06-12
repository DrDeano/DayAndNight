package main;

import java.awt.Color;
import java.awt.Graphics;

public class Bar {
	
	private double current, max, speed, change;
	private int x, y, width, height;
	private Color color;
	private long time;
	
	public Bar(Color color, double current, double max, double speed, double change, int x, int y, int width, int height){
		this.color = color;
		this.current = current;
		this.max = max;
		this.speed = speed;
		this.change = change;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		time = System.currentTimeMillis();
	}
	
	public void update(){
		long newTime = System.currentTimeMillis();
		if(newTime > time + speed){
			incrementCurrent(change);
			time = newTime;
		}
	}
	
	public void incrementCurrent(double amount){
		current += amount;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRoundRect(x, y, (int) (width * (current/max)), height, 5, 5);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, width, height, 5, 5);
	}

}
