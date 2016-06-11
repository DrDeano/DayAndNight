package serverLogic;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Stats {

	private static final double dayDecay = 0.05; // Decay per second
	private static final double nightDecay = 0.15;

	ArrayList<Player> players;


	public Stats(String[] ids) {
		super();
		this.players = new ArrayList<Player>();
		for (int i = 0; i < ids.length; i++) {
			players.add(new Player(ids[i]));
		}
	}

	public boolean finished() {
		return players.stream().anyMatch(p -> p.getStats().progress >= 100);
	}

	public void update(double delta) {
		players.forEach(p -> p.getStats().changeAll(delta * (isDay() ? dayDecay : nightDecay)));
	}

	private boolean isDay() {
		// TODO
		return true;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}



}
