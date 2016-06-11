package serverLogic;

import java.awt.geom.Point2D;

import globalClasses.Pos;

public class Player {

	private double x;
	private double y;
	private double angle;
	private String id;
	private StatContainer stats;
	private Action currentAction;


	public Player(String id) {
		super();
		this.x = 0; // TODO set starting positions
		this.y = 0;
		this.id = id;
		this.stats = new StatContainer();
	}

	public void setAction(Action action) {
		currentAction = action;
	}

	public void updatePosition(Pos newPosition) {
		x = newPosition.getPosition().getX();
		y = newPosition.getPosition().getY();
		angle = newPosition.getAngle();
	}
	public void changeStat(Stat stat, double value) {
		stats.increase(stat, value);
	}


	public Pos getPosition() {
		return new Pos(new Point2D.Double(x, y), (int) angle);
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public String getId() {
		return id;
	}
	public StatContainer getStats() {
		return stats;
	}


	private void move(double angle) {
		switch ((int) angle) {
			case 0 :
				y++;
				break;
			case 90 :
				x++;
				break;
			case 180 :
				y--;
				break;
			case 270 :
				x--;
				break;
		}
	}
	private double fixAngle(double angle) {
		while (angle < 0)
			angle += 360;
		while (angle >= 360)
			angle -= 360;
		return angle;
	}

}
