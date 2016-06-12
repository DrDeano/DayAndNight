package gameConfiguration;

import java.util.function.Function;

import globalClasses.StatContainer;
import serverLogic.CoffeeMachine;
import serverLogic.Computer;
import serverLogic.Room;
import serverLogic.Sofa;
import serverLogic.Stat;
import serverNetworking.ServerLobby;

public class ConfigStorage {

	/** Creates a simple game configuration to be used for testing. All sabotage times are 1 second. */
	public static GameConfiguration getTestConfiguration(ServerLobby lobby) {

		// Speed at which progress is generated
		Function<StatContainer, Double> speedFunction = (stats -> {
			Stat[] arguments = {Stat.COFFEE, Stat.FUN, Stat.SANITY};
			return Math.sqrt(stats.getPercentageProduct(arguments)); // Max speed is 1
		});

		// Main config: Day time, Night time (in seconds), Stat decay rate sin daytime, Stat decay rate during night (in units per second)
		GameConfiguration res = new GameConfiguration(500, 300, 0.5, 1.5, speedFunction, lobby);

		// Adding all machines

		// Sofas: x, y, width, height, use time, sabotage time, sanity restored, sanity restored when sabotaged
		res.add(new Sofa(0, 0, 2, 1, 10, 1, 40, 10));
		res.add(new Sofa(0, 2, 2, 1, 10, 1, 40, 10));
		res.add(new Sofa(0, 4, 2, 1, 10, 1, 40, 10));

		// Computers: x, y, width, height, sabotage time, progress lost from sabotage
		res.add(new Computer(5, 6, 1, 1, 1, 40, lobby));
		res.add(new Computer(7, 6, 1, 1, 1, 40, lobby));

		// Coffee machines: x, y, width, height, use time, sabotage time, coffee restored, coffee restored when sabotaged
		res.add(new CoffeeMachine(15, 5, 2, 2, 20, 1, 70, 35));
		res.add(new CoffeeMachine(12, 5, 2, 2, 10, 1, 40, 10));

		res.add(new Room(4, 4, 7, 7));
		res.add(new Room(12, 4, 6, 6));

		return res;
	}


}
