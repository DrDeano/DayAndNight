package serverLogic;

public class StatContainer {

	public double progress;
	public double coffee;
	public double fun;
	public double sanity;
	public StatContainer() {
		super();
		this.progress = 0;
		this.coffee = 50;
		this.fun = 50;
		this.sanity = 50;
	}
	
	public void changeAll(double amount){
		progress+=amount;
		coffee+=amount;
		fun+=amount;
		sanity+=amount;
	}
	
	
}
