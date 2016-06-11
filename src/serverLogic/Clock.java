package serverLogic;


public class Clock {

	private static long time;
	private static long startTime;

	public Clock() {
		startTime = System.nanoTime();
	}

	public static double getTime() {
		time = System.nanoTime() - startTime;
		return (double) (time) / (1000000000);
	}


}
