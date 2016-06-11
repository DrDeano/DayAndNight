package gameConfiguration;


public class SofaConfiguration extends MachineConfiguration {

	public double useTime;
	public double sabotageTime;
	public double sanityRestored;
	public double sanityRestoredWhenSabotaged;
	public SofaConfiguration(int x, int y, int width, int height, double useTime, double sabotageTime, double sanityRestored,
		double sanityRestoredWhenSabotaged) {
		super(x, y, width, height);
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.sanityRestored = sanityRestored;
		this.sanityRestoredWhenSabotaged = sanityRestoredWhenSabotaged;
		type = MachineType.SOFA;
	}
	public SofaConfiguration() {
		super();
		type = MachineType.SOFA;
	}



}
