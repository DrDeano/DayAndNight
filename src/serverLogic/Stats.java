package serverLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Stats {

	private static final double dayDecay = 0.05; // Decay per second
	private static final double nightDecay = 0.15;

	HashMap<String, Player> players;


	public Stats(String[] ids) {
		super();
		this.players = new HashMap<String, Player>();
		for (int i = 0; i < ids.length; i++) {
			players.put(ids[i], new Player(ids[i]));
		}
	}

	public Stats() {
		this.players = new HashMap<String, Player>();
	}
	public void addPlayer(String id) {
		players.put(id, new Player(id));
	}

	public boolean finished() {
		return players.values().stream().anyMatch(p -> p.getStats().isFinished());
	}

	public void update(double delta) {
		players.values().forEach(p -> p.getStats().changeAll(delta * (isDay() ? dayDecay : nightDecay)));
	}

	private boolean isDay() {
		// TODO
		return true;
	}


	public HashMap<String, Player> getPlayers() {
		return players;
	}



}
