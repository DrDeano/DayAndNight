package gameConfiguration;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;

import globalClasses.StatContainer;
import serverLogic.Machine;
import serverLogic.Room;
import serverNetworking.ServerLobby;

/** Object with all configuration data about the game. Includes data about all machines. */
public class GameConfiguration {

	public double dayTime;
	public double nightTime;
	public double dayDecay;
	public double nightDecay;
	public Collection<Machine> machines;
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
		this.machines = new HashSet<Machine>();
		this.rooms = new HashSet<Room>();
		this.speedFunction = speedFunction;
		this.lobby = lobby;
	}


	public void add(Machine machine) {
		machines.add(machine);
	}
	public void add(Room room) {
		rooms.add(room);
	}

	/** @return All of intractable objects */
	public Collection<Machine> getAllMachines() {
		return machines;
	}



	public double getDayTime() {
		return dayTime;
	}
	public double getNightTime() {
		return nightTime;
	}
	public Collection<Machine> getMachines() {
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
