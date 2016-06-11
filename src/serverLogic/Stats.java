package serverLogic;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Stats {

	ArrayList<StatContainer> stats;

	public Stats(int players) {
		super();
		this.stats = new ArrayList<StatContainer>();
		for (int i = 0; i < players; i++) {
			stats.add(new StatContainer());
		}
	}
	
	public boolean finished(){
		return stats.stream().anyMatch(s -> s.progress>=100);
	}
	
	
	
	
}
