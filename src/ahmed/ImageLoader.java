package ahmed;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage sofa = loadImage("/sofa.png");
	public static BufferedImage computer = loadImage("/computer.png");
	public static BufferedImage coffee = loadImage("/coffee.png");
	public static BufferedImage floor = loadImage("/floor.png");
	public static BufferedImage objects = loadImage("/objects.png");
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			System.out.println("failed to load");
			e.printStackTrace();
		}
		return null;
	}
}
