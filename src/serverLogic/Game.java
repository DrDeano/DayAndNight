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

	public void updatePosition(String playerId, Pos position) {
		stats.getPlayers().values().forEach(p -> p.updatePosition(position));
	}
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

	public Map<String, Pos> getAllPositions() {
		Hashtable<String, Pos> res = new Hashtable<String, Pos>();
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getPosition());
		}
		return res;
	}
	public Optional<Pos> getPosition(String id) {
		return stats.getPlayer(id).map(p -> p.getPosition());
	}

	public Map<String, StatContainer> getAllStats() {
		Hashtable<String, StatContainer> res = new Hashtable<String, StatContainer>();
		for (Player entry : stats.getPlayers().values()) {
			res.put(entry.getId(), entry.getStats());
		}
		return res;
	}
	public Optional<StatContainer> getStats(String id) {
		return stats.getPlayer(id).map(p -> p.getStats());
	}



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
