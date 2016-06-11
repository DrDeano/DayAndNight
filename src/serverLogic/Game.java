package serverLogic;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import globalClasses.Pos;
import globalClasses.StatContainer;

public class Game {

	private Stats stats;

	public void updatePosition(String playerId, Pos position) {
		stats.getPlayers().values().forEach(p -> p.updatePosition(position));
	}

	public Map<String, Pos> getAllPositions() {
		Hashtable<String, Pos> res = new Hashtable<String, Pos>();
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getPosition());
		}
		return res;
	}
	public Optional<Pos> getPosition(String id) {
		return stats.getPlayer(id).map(p -> p.getPosition());
	}

	public Map<String, StatContainer> getAllStats() {
		Hashtable<String, StatContainer> res = new Hashtable<String, StatContainer>();
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getStats());
		}
		return res;
	}
	public Optional<StatContainer> getStats(String id) {
		return stats.getPlayer(id).map(p -> p.getStats());
	}

}
