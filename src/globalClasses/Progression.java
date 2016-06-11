package globalClasses;

public class Progression {

	private int progress;
	private int fun;
	private int sanity;
	private int caffeine;

	public Progression(int progress, int fun, int sanity, int caffeine) {
		this.progress = progress;
		this.fun = fun;
		this.sanity = sanity;
		this.caffeine = caffeine;
	}

	public int get_progress() {
		return progress;
	}

	public int get_fun() {
		return fun;
	}

	public int get_sanity() {
		return sanity;
	}

	public int get_caffeine() {
		return caffeine;
	}
}