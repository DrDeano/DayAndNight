package serverLogic;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.HashSet;

public class Room {

	private Rectangle2D.Double bounds;
	private HashSet<Player> players;

	public Room(int x, int y, int width, int height) {
		super();
		this.bounds = new Rectangle2D.Double(x, y, width, height);
		players = new HashSet<Player>();
	}

	public boolean contains(Interactable machine) {
		return bounds.contains(machine.getRectangle());
	}

	public boolean contains(Player player) {
		return players.contains(player);
	}
	/** Update presence (or not) of players
	 * 
	 * @param allPlayers A collection with all players */
	public void update(Collection<Player> allPlayers) {
		players.forEach(p -> tryLeaving(p));
		allPlayers.stream().filter(p -> !players.contains(p)).forEach(p -> tryEntering(p));
	}


	private boolean tryLeaving(Player player) {
		if (players.contains(player)) {
			if (!bounds.contains(new Point2D.Double(player.getX(), player.getY()))) players.remove(player);
			return true;
		} else return false;
	}
	private boolean tryEntering(Player player) {
		if (bounds.contains(new Point2D.Double(player.getX(), player.getY()))) {
			players.add(player);
			return true;
		} else return false;
	}

	@Override
	public String toString() {
		String contains = "";
		for (Player player : players) {
			contains += player + "\n";
		}
		return "Room at " + bounds + ". Contains " + contains;
	}


}
