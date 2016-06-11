package globalClasses;

import java.awt.geom.Point2D;

public class Pos {
	
	private Point2D.Double coord;
	private int angle;
	
	public Pos(Point2D.Double coord, int angle) {
		this.coord = coord;
		this.angle = angle;
	}
	
	public Point2D.Double getPosition() {
		return coord;
	}
	
	public int getAngle() {
		return angle;
	}
}