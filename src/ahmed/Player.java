package ahmed;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import globalClasses.Pos;
import main.InputHandler;
import main.Main;

public class Player {

	BufferedImage image;
	Pos pos;
	int id;
	Main main;

	public Player(Main main, int id, double x, double y) {
		this.id = id;
		this.main = main;
		pos = new Pos(new Point2D.Double(x, y), 0);
		getSprite();
	}

	private void getSprite() {
		switch (id) {
		case 0:
			image = ImageLoader.character_black;
			break;
		case 1:
			image = ImageLoader.character_blue;
			break;
		case 2:
			image = ImageLoader.character_green;
			break;
		case 3:
			image = ImageLoader.character_purple;
			break;
		case 4:
			image = ImageLoader.character_yellow;
			break;
		case 5:
			image = ImageLoader.character_black;
			break;
		default:
			throw new RuntimeException("id cannot be greater than 5");
		}
	}
	
	public void update(){
//		if(i.isKeyDown(keyCode)
	}

	public void render(Graphics2D g2) {

	}
}
