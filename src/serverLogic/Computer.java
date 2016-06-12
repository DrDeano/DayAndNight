package serverLogic;

import gameConfiguration.MachineType;

public class Computer extends Interactable {

	private double sabotageTime;
	private double sabotageAmount;

	private Player owner;
	private boolean playerWorking = false;



	public Computer(int x, int y, int width, int height, double sabotageTime, double sabotageAmount, Player owner) {
		super(x, y, width, height, MachineType.COMPUTER);
		this.sabotageTime = sabotageTime;
		this.sabotageAmount = sabotageAmount;
		this.owner = owner;
	}


	public Computer(int x, int y, int width, int height, double sabotageTime, double sabotageAmount) {
		super(x, y, width, height, MachineType.COMPUTER);
		this.sabotageTime = sabotageTime;
		this.sabotageAmount = sabotageAmount;
	}
	public Computer() {
		this.sabotaged = false;
		this.type = MachineType.COMPUTER;
	}


	public void assignOwner(Player player) {
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
