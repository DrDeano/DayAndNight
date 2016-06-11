package gameConfiguration;

import java.util.ArrayList;

public class GameConfiguration {

	public double dayTime;
	public double nightTime;
	public ArrayList<MachineConfiguration> machines;
	public GameConfiguration(double dayTime, double nightTime, ArrayList<MachineConfiguration> machines) {
		super();
		this.dayTime = dayTime;
		this.nightTime = nightTime;
		this.machines = machines;
	}
	public GameConfiguration() {
		super();
	}



}
