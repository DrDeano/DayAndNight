package serverLogic;

import java.util.AbstractMap.SimpleEntry;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import globalClasses.Pos;

public class Game {

	private Stats stats;

	public void updatePosition(String playerId, Pos position) {
		stats.getPlayers().values().forEach(p -> p.updatePosition(position));
	}
	public Map<String, Pos> getAllPositions() {
		Hashtable res = new Hashtable<String, Pos>();
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getPosition());
		}
		return res;
	}



}
