package serverLogic;


public class Sofa extends Interactable {


	private double useTime;
	private double sabotageTime;
	private double sanityRestored;
	private double sanityRestoredWhenSabotaged;

	public Sofa(double useTime, double sabotageTime, double sanityRestored, double sanityRestoredWhenSabotaged) {
		super();
		this.useTime = useTime;
		this.sabotageTime = sabotageTime;
		this.sanityRestored = sanityRestored;
		this.sanityRestoredWhenSabotaged = sanityRestoredWhenSabotaged;
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
		player.changeStat(Stat.SANITY, sabotaged ? sanityRestored : sanityRestoredWhenSabotaged);
		sabotaged = false;
	}

	@Override
	public void finishSabotaging(Player player) {
		sabotaged = true;
	}

}
