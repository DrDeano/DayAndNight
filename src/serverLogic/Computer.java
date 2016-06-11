package serverLogic;


public class Computer extends Interactable {

	private double sabotageTime;
	private double sabotageAmount;

	private Player owner;
	private boolean playerWorking = false;



	public Computer(double sabotageTime, double sabotageAmount, Player owner) {
		super();
		this.sabotageTime = sabotageTime;
		this.sabotageAmount = sabotageAmount;
		this.owner = owner;
	}

	@Override
	public double startUsing(Player player) {
		if (player.equals(owner)) {
			playerWorking = true;
			(new Thread(() -> processWork())).start();
			return Double.POSITIVE_INFINITY;
		} else return 0;

	}

	private void processWork() {
		try {
			while (playerWorking) {
				Thread.sleep(500);
				owner.changeStat(Stat.PROGRESS, owner.getSpeed() / 2);
			}
		} catch (InterruptedException e) {}
	}

	@Override
	public double startSabotaging(Player player) {
		return sabotageTime;
	}

	@Override
	public void cancelAction(Player player) {
		if (player.equals(owner)) {
			playerWorking = false;
		}
	}

	@Override
	public void finishUsing(Player player) {
		playerWorking = false;

	}

	@Override
	public void finishSabotaging(Player player) {
		owner.changeStat(Stat.PROGRESS, -sabotageAmount);

	}

}
