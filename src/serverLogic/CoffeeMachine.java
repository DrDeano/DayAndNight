package serverLogic;

import gameConfiguration.MachineType;

public class CoffeeMachine extends Machine {

	private double useTime;
	private double sabotageTime;
	private double coffeeRestored;
	private double coffeeRestoredWhenSabotaged;

	public CoffeeMachine(int x, int y, int width, int height, double useTime, double sabotageTime, double coffeeRestored,
		double coffeeRestoredWhenSabotaged) {
		super(x, y, width, height, MachineType.COFFEE);
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.coffeeRestored = coffeeRestored;
		this.coffeeRestoredWhenSabotaged = coffeeRestoredWhenSabotaged;
		super.sabotaged = false;
	}

	@Override
	public double startUsing(Player player) {
		return useTime;
	}

	@Override
	public double startSabotaging(Player player) {
		return sabotageTime;
	}

	@Override
	public void cancelAction(Player player) {

	}

	@Override
	public void finishUsing(Player player) {
		if (sabotaged) player.changeStat(Stat.COFFEE, coffeeRestoredWhenSabotaged);
		else player.changeStat(Stat.COFFEE, coffeeRestored);
		sabotaged = false;
	}

	@Override
	public void finishSabotaging(Player player) {
		this.sabotaged = true;
	}

	@Override
	public String toString() {
		return "Coffee machine at x=" + rectangle.x + ", y=" + rectangle.y + ". " + (sabotaged ? "Currently sabotaged" : "Not sabotaged.");
	}

}
