package serverLogic;



import java.awt.geom.Point2D;

import gameConfiguration.ConfigStorage;
import globalClasses.Action;
import globalClasses.Pos;

public class Testing {

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game(ConfigStorage.getTestConfiguration());
		game.newPlayer("p1");
		game.newPlayer("p2");
		game.start();
		Thread.sleep(1000);

		game.players.get("p1").changeStat(Stat.PROGRESS, 80);
		game.players.get("p2").changeStat(Stat.PROGRESS, 70);
		printall(game);

		game.updatePosition("p2", new Pos(new Point2D.Double(7, 6), 0));
		Thread.sleep(1000);
		System.out.println(game.tryAction("p2", Action.SABOTAGE));

		Thread.sleep(1000);
		System.out.println(game.tryAction("p2", Action.FINISH));

		Thread.sleep(100);

		printall(game);
	}

	public static void printall(Game game) {
		System.out.println("-----Players-----");
		game.players.values().forEach(p -> System.out.println(p + " " + p.getPrintableStats()));
		System.out.println("\n-----Machines-----");
		game.machines.forEach(m -> System.out.println(m));
		System.out.println("\n-----Rooms-----");
		game.rooms.forEach(r -> System.out.println(r));
		System.out.println("Time =" + Clock.getTime());
	}



}
