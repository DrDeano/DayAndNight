package serverLogic;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Stats {

	private static final double dayDecay = 0.05; // Decay per second
	private static final double nightDecay = 0.15;

	ArrayList<Player> players;


	public Stats(int amountOfPlayers) {
		super();
		this.players = new ArrayList<Player>();
		for (int i = 0; i < amountOfPlayers; i++) {
			players.add(new Player(i));
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


}
