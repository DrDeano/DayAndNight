package serverLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import gameConfiguration.GameConfiguration;
import globalClasses.Action;
import globalClasses.ActionResponse;
import globalClasses.Pos;
import globalClasses.StatContainer;

public class Game {

	private Collection<Interactable> machines;
	private Collection<Room> rooms;
	private Function<StatContainer, Double> speedFunction;
	private HashMap<String, Player> players;

	private GameConfiguration config;
	private double dayTime;
	private double nightTime;
	private boolean nightStarted = false;

	/** Creates a new game and starts the internal clock. */
	public Game(GameConfiguration config) {
		players = new HashMap<String, Player>();
		rooms = config.getRooms();
		machines = config.getMachines();
		machines.forEach(m -> m.findRoom(rooms)); // Sets rooms the machines are in
		speedFunction = config.getSpeedFunction();
		this.config = config;
	}

	public void start() {
		new Clock();
		(new Thread(() -> mainLoop())).start();
	}

	/** Update the position of a single player
	 * 
	 * @param playerId Id of the player
	 * @param position new position */
	public void updatePosition(String playerId, Pos position) {
		players.values().forEach(p -> p.updatePosition(position));
	}
	/** Execute an action as a player. Call this when a player sends a new action (not movement).
	 * 
	 * @param playerId
	 * @param action
	 * @return */
	public Optional<ActionResponse> tryAction(String playerId, Action action) {
		Player player = players.get(playerId);
		switch (action) {
			case NONE :
				return Optional.empty();
			case CANCEL :
				player.getMachine().ifPresent(m -> {
					m.cancelAction(player);
					player.setMachine(null);
				});
				return Optional.empty();
			case USE : {
				Optional<Interactable> machine = getClosest(player);
				if (machine.isPresent()) {
					player.setMachine(machine.get());
					double timeLeft = machine.get().startUsing(player);
					return Optional.of(new ActionResponse(Optional.of(timeLeft), true));
				} else {
					return Optional.of(new ActionResponse(Optional.empty(), false));
				}
			}
			case SABOTAGE : {
				Optional<Interactable> machine = getClosest(player);
				if (machine.isPresent()) {
					player.setMachine(machine.get());
					double timeLeft = machine.get().startSabotaging(player);
					if (timeLeft < 0) return Optional.of(new ActionResponse(Optional.empty(), false));
					else return Optional.of(new ActionResponse(Optional.of(timeLeft), true));
				} else {
					return Optional.of(new ActionResponse(Optional.empty(), false));
				}
			}
			default :
				return Optional.empty();

		}
	}

	/** Get a map of player id's to positions */
	public Map<String, Pos> getAllPositions() {
		Hashtable<String, Pos> res = new Hashtable<String, Pos>();
		for (Player entry : players.values()) {
			res.put(entry.getId(), entry.getPosition());
		}
		return res;
	}
	/** Get a position of a specific player
	 * 
	 * @param id Id of that player */
	public Optional<Pos> getPosition(String id) {
		return Optional.ofNullable(players.get(id)).map(p -> p.getPosition());
	}

	/** Get a map of player id's to their stats */
	public Map<String, StatContainer> getAllStats() {
		Hashtable<String, StatContainer> res = new Hashtable<String, StatContainer>();
		for (Player entry : players.values()) {
			res.put(entry.getId(), entry.getStats());
		}
		return res;
	}
	/** Get stats for a player
	 * 
	 * @param id Player id
	 * @return Stats for a player. Empty Optional if player doesn't exist. */
	public Optional<StatContainer> getStats(String id) {
		return Optional.ofNullable(players.get(id)).map(p -> p.getStats());
	}

	/** Add a new player
	 * 
	 * @param id Id of the new player */
	public void newPlayer(String id) {
		players.put(id, new Player(id, speedFunction));
	}

	/** Add a new machine */
	public void addMachine(Interactable machine) {
		machines.add(machine);
	}
	/** Add all machines in a collection */
	public void addMachines(Collection<Interactable> machines) {
		machines.forEach(m -> addMachine(m));
	}


	private void mainLoop() {
		try {
			while (true) {
				if (Clock.getTime() > config.getDayTime()) nightStarted = true;
				rooms.forEach(r -> r.update(players.values()));
				double decay = nightStarted ? config.getDayDecay() : config.getNightDecay();
				players.values().forEach(p -> p.getStats().decay(decay * 0.05));
				if (players.values().stream().anyMatch(p -> p.getStats().isFinished())) break; // TODO Actual game ending condition
				Thread.sleep(50);
			}
		} catch (InterruptedException ex) {
			System.err.println("Main game loop interrupted with " + ex);
		}
	}



	/** Get the machine closest to a player
	 * 
	 * @param player
	 * @return Intractable object closest to the player, if they can interact with it. Empty Optional if nothing is in range. */
	private Optional<Interactable> getClosest(Player player) {
		Interactable res = null;
		for (Interactable machine : machines) {
			if (machine.canReach(player)) {
				if (res == null) res = machine;
				else if (res.getDistance(player) > machine.getDistance(player)) {
					res = machine;
				}
			}
		}
		return Optional.ofNullable(res);
	}



}
