package serverLogic;


public class Player {

	private int x;
	private int y;
	private int id;
	private StatContainer stats;
	private double moveTimeLeft = 0;


	public Player(int id) {
		super();
		this.x = 0; // TODO set starting positions
		this.y = 0;
		this.id = id;
		this.stats = new StatContainer();
	}


	private void move(double angle) {
		switch ((int) angle) {
			case 0 :
				y++;
				break;
			case 90 :
				x++;
				break;
			case 180 :
				y--;
				break;
			case 270 :
				x--;
				break;
		}
	}
	private double fixAngle(double angle) {
		while (angle < 0)
			angle += 360;
		while (angle >= 360)
			angle -= 360;
		return angle;
	}



	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getId() {
		return id;
	}
	public StatContainer getStats() {
		return stats;
	}
	public boolean isMoving() {
		return moveTimeLeft > 0;
	}



}
