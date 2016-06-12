package main;

import java.awt.Graphics;
import java.awt.Image;

public class Block {

	public static final Block sofa = new Block("sofa.png", 0, true, 32, 16);
	public static final Block computer = new Block("computer.png", 0, true, 80, 32);
	public static final Block coffee = new Block("coffeeMaker.png", 1000, true, 32, 32);
	public static final Block floor = new Block("carpet.png", 0, false, 16, 16);
	public static final Block wall = new Block("wallsprites.png", 0, true, 16, 16);

	private Image[] textures;
	private long time, animationWaitTime;
	private int index, width, height;
	private boolean solid;

	public Block(String path, long animationWaitTime, boolean solid, int width, int height) {
		textures = ResourceLoader.getBlockSprites(path, width, height);
		this.animationWaitTime = animationWaitTime;
		time = System.currentTimeMillis();
		this.setSolid(solid);
		this.setWidth(width);
		this.setHeight(height);
	}

	public void update() {
		long newTime = System.currentTimeMillis();
		if (newTime > time + animationWaitTime) {
			time = newTime;
			if (index < textures.length) {
				index++;
			} else {
				index = 0;
			}
		}
	}
	
	public Image getImage(){
		return textures[0];
	}
	
	public void chooseTile(int index){
		if(index > textures.length || index < 0){
			this.index = 0;
		}else{
			this.index = index;
		}
	}

	public void draw(Graphics g2d, int x, int y) {
		g2d.drawImage(textures[index], x, y, width, height, null);
	}

	public void draw(Graphics g2d, int x, int y, int width, int height) {
		g2d.drawImage(textures[index], x, y, width, height, null);
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
