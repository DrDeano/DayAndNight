package gameConfiguration;


public abstract class MachineConfiguration {

	public int x;
	public int y;
	public int width;
	public int height;
	public MachineType type;

	public MachineConfiguration(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public MachineConfiguration() {
		super();
	}

}
