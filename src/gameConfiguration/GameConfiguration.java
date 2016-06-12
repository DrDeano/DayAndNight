package gameConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

import globalClasses.StatContainer;
import serverLogic.Interactable;

public class GameConfiguration {

	public double dayTime;
	public double nightTime;
	public Collection<Interactable> machines;
	public Function<StatContainer, Double> speedFunction;

	public GameConfiguration(double dayTime, double nightTime, Function<StatContainer, Double> speedFunction) {
		super();
		this.dayTime = dayTime;
		this.nightTime = nightTime;
		this.machines = new LinkedList<Interactable>();
		this.speedFunction = speedFunction;
	}
	public GameConfiguration(double dayTime, double nightTime) {
		super();
		this.dayTime = dayTime;
		this.nightTime = nightTime;
		this.machines = new LinkedList<Interactable>();
	}
	public GameConfiguration(double dayTime, double nightTime, Collection<Interactable> machines) {
		super();
		this.dayTime = dayTime;
		this.nightTime = nightTime;
		this.machines = machines;
	}
	public GameConfiguration(double dayTime, double nightTime, Interactable... machines) {
		super();
		this.dayTime = dayTime;
		this.nightTime = nightTime;
		this.machines = new ArrayList<Interactable>();
		for (int i = 0; i < machines.length; i++) {
			this.machines.add(machines[i]);
		}
	}
	public GameConfiguration() {
		super();
	}

	public void add(Interactable machine) {
		machines.add(machine);
	}



	public double getDayTime() {
		return dayTime;
	}

	public double getNightTime() {
		return nightTime;
	}

	public Collection<Interactable> getMachines() {
		return machines;
	}

	public Function<StatContainer, Double> getSpeedFunction() {
		return speedFunction;
	}



}
