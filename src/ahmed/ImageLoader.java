package ahmed;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import utils.ResourceLoader;

public class ImageLoader {
	
	public static BufferedImage sofa = ResourceLoader.getBufferedImage("sofa.png");
	public static BufferedImage computer = ResourceLoader.getBufferedImage("computer.png");
	public static BufferedImage coffee = ResourceLoader.getBufferedImage("coffeeMaker.png");
	public static BufferedImage floor = ResourceLoader.getBufferedImage("carpet.png");
	public static BufferedImage objects = ResourceLoader.getBufferedImage("objects.png");
	

}
