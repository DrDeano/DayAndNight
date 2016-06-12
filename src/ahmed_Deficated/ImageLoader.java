package ahmed_Deficated;
import java.awt.image.BufferedImage;

import main.ResourceLoader;


public class ImageLoader {
	
	// objects
	public static BufferedImage sofa = ResourceLoader.getBufferedImage("sofa.png");
	public static BufferedImage computer = ResourceLoader.getBufferedImage("computer.png");
	public static BufferedImage coffee = ResourceLoader.getBufferedImage("coffeeMaker.png");
	public static BufferedImage floor = ResourceLoader.getBufferedImage("carpet.png");
	
	// the map
	public static BufferedImage objects = ResourceLoader.getBufferedImage("objects.png");
	
	// player sprites
	public static BufferedImage character_black = ResourceLoader.getBufferedImage("character_sprites_black.png");
	public static BufferedImage character_blue = ResourceLoader.getBufferedImage("character_sprites_blue.png");
	public static BufferedImage character_green = ResourceLoader.getBufferedImage("character_sprites_green.png");
	public static BufferedImage character_purple = ResourceLoader.getBufferedImage("character_sprites_purple.png");
	public static BufferedImage character_yellow = ResourceLoader.getBufferedImage("character_sprites_yellow.png");


}
