package serverLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StatContainer {

	public final double minValue = 0;
	public final double maxValue = 100;
	public final double startingValue = 50;

	private HashMap<Stat, Double> table;

	public StatContainer() {
		super();
		table = new HashMap<Stat, Double>();
		table.put(Stat.PROGRESS, 0.0);
		table.put(Stat.COFFEE, 0.0);
		table.put(Stat.FUN, 0.0);
		table.put(Stat.SANITY, 0.0);
	}

	public void changeAll(double value) {
		for (Entry<Stat, Double> entry : table.entrySet()) {
			entry.setValue(entry.getValue() + value);
		}
	}

	public void increase(Stat stat, double value) {
		table.put(stat, value);
	}

	public boolean isFinished() {
		return table.get(Stat.PROGRESS) >= 100;
	}


}
