package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity {

	protected Image[][] sprites;
	protected String name;
	protected int currentISprite, currentJSprite, xPos, yPos, width, height, totalHealth, currentHealth;
	protected boolean isDead = false;
	protected Rectangle rectUp, rectLeft, rectDown, rectRight;
	protected boolean collideUp, collideLeft, collideDown, collideRight;
	
	public Entity(String name, String spritePath, int xPos, int yPos, int width, int height){
		setName(name);
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		rectUp = new Rectangle(xPos + width / 8, yPos, 6 * width / 8, height / 8);
		rectLeft = new Rectangle(xPos, yPos + height / 8, width / 8, 6 * height / 8);
		rectDown = new Rectangle(xPos + width / 8, yPos + 7 * height / 8, 6 * width / 8, height / 8);
		rectRight = new Rectangle(xPos + 7 * width / 8, yPos + height / 8, width / 8, 6 * height / 8);
		setSprites(spritePath, width, height);
	}

	public abstract void update();

	public abstract void draw(Graphics2D g2d);
	
	public boolean collideUp(Rectangle rect){
		if(rect.intersects(rectUp)){
			return true;
		}
		return false;
	}
	
	public boolean collideLeft(Rectangle rect){
		if(rect.intersects(rectLeft)){
			return true;
		}
		return false;
	}
	
	public boolean collideDown(Rectangle rect){
		if(rect.intersects(rectDown)){
			return true;
		}
		return false;
	}
	
	public boolean collideRight(Rectangle rect){
		if(rect.intersects(rectRight)){
			return true;
		}
		return false;
	}
	
	public void inflictDamage(int amount){
		currentHealth -= amount;
		if(currentHealth < 0){
			isDead = true;
		}
	}
	
	public void heal(int amount){
		if(currentHealth + amount > totalHealth){
			currentHealth = totalHealth;
		}else{
			currentHealth += amount;
		}
	}
	
	public void moveUp(double amount){
		yPos += amount;
	}
	
	public void moveLeft(double amount){
		xPos += amount;
	}
	
	public void moveDown(double amount){
		yPos -= amount;
	}
	
	public void moveRight(double amount){
		xPos -= amount;
	}
	
	public void moveUL(double amount){
		xPos += amount;
		yPos += amount;
	}
	
	public void moveUR(double amount){
		xPos -= amount;
		yPos += amount;
	}
	
	public void moveDL(double amount){
		xPos += amount;
		yPos -= amount;
	}
	
	public void moveDR(double amount){
		xPos -= amount;
		yPos -= amount;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Image[][] getSprites(){
		return sprites;
	}

	public void setSprites(String spritePath, int width, int height){
		sprites = ResourceLoader.getPlayerSprites(spritePath, width, height);
	}

	public int getXPos(){
		return xPos;
	}

	public void setXPos(int xPos){
		this.xPos = xPos;
	}

	public int getYPos(){
		return yPos;
	}

	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	public int getWidth(){
		return width;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getHeight(){
		return height;
	}

	public void setHeight(int height){
		this.height = height;
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public void setIsDead(boolean isDead){
		this.isDead = isDead;
	}

	public int getCurrentISprite() {
		return currentISprite;
	}

	public void setCurrentISprite(int currentISprite) {
		this.currentISprite = currentISprite;
	}

	public int getCurrentJSprite() {
		return currentJSprite;
	}

	public void setCurrentJSprite(int currentJSprite) {
		this.currentJSprite = currentJSprite;
	}
}
