package serverLogic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import globalClasses.Action;
import globalClasses.ActionResponse;
import globalClasses.Pos;
import globalClasses.StatContainer;

public class Game {

	private Stats stats;
	private ArrayList<Interactable> machines;

	/** Update the position of a single player
	 * 
	 * @param playerId Id of the player
	 * @param position new position */
	public void updatePosition(String playerId, Pos position) {
		stats.getPlayers().values().forEach(p -> p.updatePosition(position));
	}
	/** Execute an action as a player. Call this when a player sends a new action (not movement).
	 * 
	 * @param playerId
	 * @param action
	 * @return */
	public Optional<ActionResponse> tryAction(String playerId, Action action) {
		Player player = stats.getPlayers().get(playerId);
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
					return Optional.of(new ActionResponse(Optional.of(timeLeft), true));
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
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getPosition());
		}
		return res;
	}
	/** Get a position of a specific player
	 * 
	 * @param id Id of that player */
	public Optional<Pos> getPosition(String id) {
		return stats.getPlayer(id).map(p -> p.getPosition());
	}

	/** Get a map of player id's to their stats */
	public Map<String, StatContainer> getAllStats() {
		Hashtable<String, StatContainer> res = new Hashtable<String, StatContainer>();
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getStats());
		}
		return res;
	}
	/** Get stats for a player
	 * 
	 * @param id Player id
	 * @return Stats for a player. Empty Optional if player doesn't exist. */
	public Optional<StatContainer> getStats(String id) {
		return stats.getPlayer(id).map(p -> p.getStats());
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
