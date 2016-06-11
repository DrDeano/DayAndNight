package gameConfiguration;

import java.util.ArrayList;

import com.google.gson.Gson;

public class GsonTesting {

	public static void main(String[] args) {
		CoffeeConfiguration coffee = new CoffeeConfiguration(3, 3, 1, 1, 5, 5, 40, 10);
		SofaConfiguration sofa = new SofaConfiguration(10, 10, 2, 1, 10, 5, 50, 30);
		ComputerConfiguration computer = new ComputerConfiguration(5, 5, 1, 1, 7, 20);
		ArrayList<MachineConfiguration> machines = new ArrayList<MachineConfiguration>();
		machines.add(computer);
		machines.add(coffee);
		machines.add(sofa);
		GameConfiguration game = new GameConfiguration(600, 300, machines);
		Gson gson = new Gson();
		String config = gson.toJson(game);
		System.out.println(config);

		GameConfiguration game2 = gson.fromJson(config, GameConfiguration.class);
		System.out.println(game2);
		System.out.println(game2.machines.get(1).);

	}

}
