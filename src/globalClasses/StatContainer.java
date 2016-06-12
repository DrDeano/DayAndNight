package globalClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import serverLogic.Stat;

public class StatContainer {

	public final double minValue = 0;
	public final double maxValue = 100;
	public final double startingValue = 50;

	private HashMap<Stat, Double> table;

	public StatContainer() {
		super();
		table = new HashMap<Stat, Double>();
		table.put(Stat.PROGRESS, 0.0);
		table.put(Stat.COFFEE, startingValue);
		table.put(Stat.FUN, startingValue);
		table.put(Stat.SANITY, startingValue);
	}

	public void changeAll(double value) {
		for (Entry<Stat, Double> entry : table.entrySet()) {
			entry.setValue(entry.getValue() + value);
		}
		correctAllStats();
	}
	/** Decreases all values except for progress by a specified amount
	 * 
	 * @param value Value to decrease stats by. Sign is ignored (stats always go down). */
	public void decay(double value) {
		for (Entry<Stat, Double> entry : table.entrySet()) {
			if (!entry.getKey().equals(Stat.PROGRESS)) entry.setValue(entry.getValue() + -Math.abs(value));
		}
	}

	public void increase(Stat stat, double value) {
		table.put(stat, table.get(stat) + value);
		correctStat(stat);

	}


	public double get(Stat stat) {
		return table.get(stat);
	}
	public double getPercentageProduct(Stat... stats) {
		double res = 1;
		for (int i = 0; i < stats.length; i++) {
			res *= table.get(stats[i]) / maxValue;
		}
		return res;
	}

	public boolean isFinished() {
		return table.get(Stat.PROGRESS) >= 100;
	}

	private void correctAllStats() {
		for (Entry<Stat, Double> entry : table.entrySet()) {
			if (entry.getValue() < minValue) entry.setValue(minValue);
			else if (entry.getValue() > maxValue) entry.setValue(maxValue);
		}
	}
	private void correctStat(Stat stat) {
		if (table.get(stat) < minValue) table.put(stat, minValue);
		else if (table.get(stat) > maxValue) table.put(stat, maxValue);
	}

	public String printableStats() {
		String res = "";
		for (Entry<Stat, Double> stat : table.entrySet()) {
			res += stat.getKey() + " = " + stat.getValue() + "\n";
		}
		return res;
	}


}
