package ahmed;
import java.awt.Graphics2D;

public class Handler {

	Launcher launcher;
	Level level;

	public Handler(Launcher launcher) {
		this.launcher = launcher;
		init();
	}

	public void init() {
		level = new Level(this);
		level.init();
	}

	public void update() {
		level.update();
	}

	public void render(Graphics2D g2) {
		level.render(g2);
	}
}
