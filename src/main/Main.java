package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static int FPS;
	private static float SPF;
	private static long currentTime, previousTime, deltaTime;

	public InputHandler input;
	public Level level;
	public static int width,height;
	public static float ratio, tilesW, tilesH, widthpx, heightpx, zerXCoord, zeroYCoord, root2 = (float) Math.sqrt(2.0);
	private static float deltas, speed = 16;

	public static void main(String args[]) {
		Main main = new Main();
		
		main.run();
	}

	public void run() {
		initialise();
		long minute = previousTime;
		int loops = 0;
		long time = previousTime + 1000000000l;
		while (running) {
			currentTime = System.nanoTime();
			deltaTime = currentTime - previousTime;
			deltas = ((float) deltaTime / 1000000000f);
			minute += deltaTime;
			loops++;
			if (minute >= time) {
				time += 1000000000l;
				loops = 0;
			}
			update(deltas);
			draw();
			previousTime = currentTime;
		}
	}

	public void initialise() {
		running = true;
		input = new InputHandler(this);
		previousTime = System.nanoTime();
		FPS = this.getGraphicsConfiguration().getDevice().getDisplayMode().getRefreshRate();
		SPF = 1f / FPS;
		this.setTitle("Day And Night");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(InputHandler.screenSize);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		width = this.getWidth();
		height = this.getHeight();
		
		ratio = (float)width / (float)height;
		int tiles = 16;
		if(height > width){
			tilesH = tiles;
			tilesW = ratio * (float)tiles;
		}else{
			tilesW = tiles;
			tilesH = (float)tiles / ratio;
		}
		heightpx = (float)height / tilesH;
		widthpx = (float)width / tilesW;
		level = new Level(this);
		level.init();
	}
	
	// 0,0 top-left
	// left +
	// up +
	public void moveUp(){
		if(zeroYCoord < -height){
		zeroYCoord += speed;
		}
	}
	public void moveDown(){
		if(zeroYCoord > -level.getYSize()){
			zeroYCoord -= speed;
		}
	}
	public void moveRight(){
		if(zerXCoord > -level.getXSize()){
			zerXCoord -= speed;
		}
	}
	public void moveLeft(){
		if(zerXCoord < width){
			zerXCoord += speed;
		}
	}
	public void moveUR(){
		if(zeroYCoord >= height){
			moveRight();
		}else if(zerXCoord <= -level.getXSize()){
			moveUp();
		}else{
			zeroYCoord += speed/root2;
			zerXCoord -= speed/root2;
		}
	}
	public void moveUL(){
		if(zeroYCoord >= height){
			moveLeft();
		}else if(zerXCoord >= 0){
			moveUp();
		}else{
			zeroYCoord += speed/root2;
			zerXCoord += speed/root2;
		}
	}
	public void moveDR(){
		if(zeroYCoord <= -level.getYSize()){
			moveRight();
		}else if(zerXCoord <= -level.getXSize()){
			moveDown();
		}else{
			zeroYCoord -= speed/root2;
			zerXCoord -= speed/root2;
		}
	}
	public void moveDL(){
		if(zeroYCoord <= -level.getYSize()){
			moveLeft();
		}else if(zerXCoord >= 0){
			moveDown();
		}else{
			zeroYCoord -= speed/root2;
			zerXCoord += speed/root2;
		}
	}
	
	private void update(float deltaTime){
		
		if(input.isKeyDown(KeyEvent.VK_W)){
			if(input.isKeyDown(KeyEvent.VK_D)){
				moveUR();
			}else if(input.isKeyDown(KeyEvent.VK_A)){
				moveUL();
			}else{
				moveUp();
			}
		}else if(input.isKeyDown(KeyEvent.VK_S)){
			if(input.isKeyDown(KeyEvent.VK_D)){
				moveDR();
			}else if(input.isKeyDown(KeyEvent.VK_A)){
				moveDL();
			}else{
				moveDown();
			}
		}else if(input.isKeyDown(KeyEvent.VK_D)){
			moveRight();
		}else if(input.isKeyDown(KeyEvent.VK_A)){
			moveLeft();
		}
		
		if(input.isKeyDown(KeyEvent.VK_ESCAPE)){
			System.exit(0);
		}
		level.update();
		
	}
	

	public void draw() {

		Graphics g = this.getGraphics();
		Image offImage = this.createImage(width, height);
		Graphics offGraphics = offImage.getGraphics();
		offGraphics.setColor(Color.BLACK);
		offGraphics.fillRect(0, 0, width, height);
		
		level.draw(offGraphics);
		offGraphics.setColor(Color.red);
		offGraphics.fillRect(width/2 - Level.tileSize/2, height/2 - Level.tileSize/2, Level.tileSize, Level.tileSize);
		g.drawImage(offImage, (int)zerXCoord, (int)zeroYCoord, this.getWidth(), this.getHeight(), null);

	}

}
