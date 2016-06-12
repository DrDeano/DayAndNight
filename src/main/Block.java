package main;

import java.awt.Graphics2D;
import java.awt.Image;

public class Block {
	
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
	
	public void update(){
		long newTime = System.currentTimeMillis();
		if(newTime > time + animationWaitTime){
			time = newTime;
			if(index < textures.length){
				index ++;
			}else{
				index = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2d, int x, int y){
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
