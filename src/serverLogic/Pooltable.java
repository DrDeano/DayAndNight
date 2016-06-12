package serverLogic;

import gameConfiguration.MachineType;

public class Pooltable extends Interactable {

	private double useTime;
	private double sabotageTime;
	private double funRestored;
	private double funRestoredWhenSabotaged;



	public Pooltable(int x, int y, int width, int height, double useTime, double sabotageTime, double funRestored,
		double funRestoredWhenSabotaged) {
		super(x, y, width, height, MachineType.POOL);
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.funRestored = funRestored;
		this.funRestoredWhenSabotaged = funRestoredWhenSabotaged;
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
		player.changeStat(Stat.FUN, sabotaged ? funRestored : funRestoredWhenSabotaged);
		sabotaged = false;
	}

	@Override
	public void finishSabotaging(Player player) {
		sabotaged = true;
	}

	@Override
	public String toString() {
		return "Pool table at x=" + rectangle.x + ", y=" + rectangle.y + ". " + (sabotaged ? "Currently sabotaged" : "Not sabotaged.");
	}

}
