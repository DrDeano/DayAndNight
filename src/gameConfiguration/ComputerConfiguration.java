package gameConfiguration;


public class ComputerConfiguration extends MachineConfiguration {

	private double sabotageTime;
	private double sabotageAmount;
	public ComputerConfiguration(int x, int y, int width, int height, double sabotageTime, double sabotageAmount) {
		super(x, y, width, height);
		this.sabotageTime = sabotageTime;
		this.sabotageAmount = sabotageAmount;
		type = MachineType.COMPUTER;
	}
	public ComputerConfiguration() {
		super();
		type = MachineType.COMPUTER;
	}


}
