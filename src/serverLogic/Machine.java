package serverLogic;


import java.awt.geom.Rectangle2D;
import java.util.Collection;

import gameConfiguration.MachineType;

/** Abstract machine, containing a position rectangle */
public abstract class Machine {

	public static final double interactionDistance = 1;

	public MachineType type;
	protected Rectangle2D.Double rectangle;
	protected Room room;
	protected boolean sabotaged;



	public Machine(int x, int y, int width, int height, MachineType type) {
		super();
		this.rectangle = new Rectangle2D.Double(x, y, width, height);
		this.type = type;
	}

	public boolean canReach(Player player) {
		return getDistance(player) <= interactionDistance;
	}

	public double getDistance(Player player) {
		double x = player.getX();
		double y = player.getY();

		double dx = Math.min(Math.abs(rectangle.getMinX() - x), Math.abs(rectangle.getMaxX() - x));
		if (rectangle.getMinX() < x && x < rectangle.getMaxX()) dx = 0;

		double dy = Math.min(Math.abs(rectangle.getMinY() - y), Math.abs(rectangle.getMaxY() - y));
		if (rectangle.getMinY() < y && y < rectangle.getMaxY()) dy = 0;

		return dx + dy;
	}


	/** Find a room in which this machine is located. Computers must be in a room, for other machines it's optional.
	 * 
	 * @param rooms Collection with all rooms in the game. */
	public void findRoom(Collection<Room> rooms) {
		rooms.stream().filter(r -> r.contains(this)).findAny().ifPresent(r -> room = r);
	}

	public boolean tryAssigningPlayer(Player player) {
		try {
			Computer computer = (Computer) this;
			if (!computer.hasOwner()) {
				computer.assignOwner(player);
				return true;
			} else return false;
		} catch (ClassCastException ex) {
			return false;
		}
	}


	/** @return The rectangle of the machine */
	public Rectangle2D.Double getRectangle() {
		return rectangle;
	}
	/** @return Type of the machine */
	public MachineType getType() {
		return type;
	}

	abstract public double startUsing(Player player);
	abstract public double startSabotaging(Player player);

	abstract public void cancelAction(Player player);

	abstract public void finishUsing(Player player);
	abstract public void finishSabotaging(Player player);



}
