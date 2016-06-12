package serverLogic;

import gameConfiguration.ConfigStorage;

public class Testing {

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game(ConfigStorage.getTestConfiguration());
		game.newPlayer("p1");
		game.newPlayer("p2");
		game.start();
		Thread.sleep(1000);



	}


}
