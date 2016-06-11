package serverLogic;


public class CoffeeMachine extends Interactable {

	private double useTime;
	private double sabotageTime;
	private double coffeeRestored;
	private double coffeeRestoredWhenSabotaged;

	private boolean sabotaged;

	public CoffeeMachine(double useTime, double sabotageTime, double coffeeRestored, double coffeeRestoredWhenSabotaged) {
		super();
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.coffeeRestored = coffeeRestored;
		this.coffeeRestoredWhenSabotaged = coffeeRestoredWhenSabotaged;
		this.sabotaged = false;
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

}
