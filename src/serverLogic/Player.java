package serverLogic;

import java.awt.geom.Point2D;
import java.util.Optional;
import java.util.function.Function;

import globalClasses.Pos;
import globalClasses.StatContainer;

public class Player {

	private double x;
	private double y;
	private double angle;
	private String id;
	private StatContainer stats;
	private Function<StatContainer, Double> speedFunction;

	private Interactable interactingWith = null;


	public Player(String id, Function<StatContainer, Double> speedFunction) {
		super();
		this.x = 0; // TODO set starting positions
		this.y = 0;
		this.id = id;
		this.stats = new StatContainer();
		this.speedFunction = speedFunction;
	}

	public void updatePosition(Pos newPosition) {
		x = newPosition.getPosition().getX();
		y = newPosition.getPosition().getY();
		angle = fixAngle(newPosition.getAngle());
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
	public void setMachine(Interactable machine) {
		this.interactingWith = machine;
	}
	public Optional<Interactable> getMachine() {
		return Optional.ofNullable(interactingWith);
	}
	public double getSpeed() {
		return speedFunction.apply(stats);
	}

	private double fixAngle(double angle) {
		while (angle < 0)
			angle += 360;
		while (angle >= 360)
			angle -= 360;
		return angle;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return ((Player) obj).getId().equals(this.getId());
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
