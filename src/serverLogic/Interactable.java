package serverLogic;

import java.awt.geom.Rectangle2D;

public abstract class Interactable {

	Rectangle2D rectangle;
	public static final double interactionDistance = 1;

	public boolean canReach(Player player) {
		return getDistance(player) <= interactionDistance;
	}

	private double getDistance(Player player) {
		return Math.sqrt(Math.pow((rectangle.getCenterX() - player.getX()), 2) + Math.pow((rectangle.getCenterY() - player.getY()), 2));
		// TODO Get distance from rectangle and not from the point
	}

	abstract public double startUsing(Player player);
	abstract public double startSabotaging(Player player);

	abstract public void cancelAction(Player player);

	abstract public void finishUsing(Player player);
	abstract public void finishSabotaging(Player player);



}
