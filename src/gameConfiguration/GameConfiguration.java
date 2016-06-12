package gameConfiguration;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;

import globalClasses.StatContainer;
import serverLogic.Interactable;
import serverLogic.Room;
import serverNetworking.ServerLobby;

public class GameConfiguration {

	public double dayTime;
	public double nightTime;
	public double dayDecay;
	public double nightDecay;
	public Collection<Interactable> machines;
	public Collection<Room> rooms;
	public Function<StatContainer, Double> speedFunction;

	public ServerLobby lobby;

	public GameConfiguration(double dayTime, double nightTime, double dayDecay, double nightDecay,
		Function<StatContainer, Double> speedFunction, ServerLobby lobby) {
		super();
		this.dayTime = dayTime;
		this.nightTime = nightTime;
		this.dayDecay = dayDecay;
		this.nightDecay = nightDecay;
		this.machines = new HashSet<Interactable>();
		this.rooms = new HashSet<Room>();
		this.speedFunction = speedFunction;
		this.lobby = lobby;
	}


	public void add(Interactable machine) {
		machines.add(machine);
	}
	public void add(Room room) {
		rooms.add(room);
	}

	/** @return All of intractable objects */
	public Collection<Interactable> getAllMachines() {
		return machines;
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
	public Collection<Room> getRooms() {
		return rooms;
	}
	public double getDayDecay() {
		return dayDecay;
	}
	public double getNightDecay() {
		return nightDecay;
	}
	public ServerLobby getLobby() {
		return lobby;
	}



}
