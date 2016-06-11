package gameConfiguration;


public class CoffeeConfiguration extends MachineConfiguration {

	public double useTime;
	public double sabotageTime;
	public double coffeeRestored;
	public double coffeeRestoredWhenSabotaged;
	public CoffeeConfiguration(int x, int y, int width, int height, double useTime, double sabotageTime, double coffeeRestored,
		double coffeeRestoredWhenSabotaged) {
		super(x, y, width, height);
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.coffeeRestored = coffeeRestored;
		this.coffeeRestoredWhenSabotaged = coffeeRestoredWhenSabotaged;
		type = MachineType.COFFEE;
	}
	public CoffeeConfiguration() {
		super();
		type = MachineType.COFFEE;
	}



}
