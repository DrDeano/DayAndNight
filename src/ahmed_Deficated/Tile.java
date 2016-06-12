package ahmed_Deficated;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile {
	
	//static int scale = 1;
	
	int id=2;
	BufferedImage image;
	int tileColor;
	boolean solid;
	
	static ArrayList<Tile> objectTiles = new ArrayList<Tile>();
	
	static int tileSize = 16;
	
	public static Tile sofa = new Tile(0, ImageLoader.sofa, 0xFFffaec9, true);
	public static Tile computer = new Tile(1, ImageLoader.computer, 0xFFed1c24, true);
	public static Tile coffee = new Tile(2, ImageLoader.coffee, 0xFFB97A57, true);
	public static Tile floor = new Tile(3, ImageLoader.floor, 0xFFff7f27, false);
	
	
	public Tile(int id, BufferedImage image, int tileColor, boolean solid){
		this.id = id;
		this.image=image;
		this.solid=solid;
		this.tileColor=tileColor;
		objectTiles.add(this);
	}
	
	public void interact(Object o){
		
	}
	
	public void render(Graphics2D g2, int x, int y, int width, int height){
//		System.out.println("Drawing: "+id+" at x: "+x+" :y "+y);
		g2.drawImage(image,x, y, width, height,null);
//		g2.drawImage(ImageLoader.objects, 0, 0, null);
	}
	
}
