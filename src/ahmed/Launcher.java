package ahmed;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class Launcher extends Canvas implements Runnable {

	GameFrame gameFrame;
	
	private Input input = new Input(this);

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 600;

	private Dimension size = new Dimension(WIDTH+6, HEIGHT+28);

	private BufferStrategy bs;
	private Graphics g;

	private Handler handler;

	private boolean running;

	private int ticks;
	public Launcher() {
		init();
	}

	public synchronized void start() {
		Thread thread = new Thread(this);
		running = true;
		thread.start();
	}

	public void run() {
		int fps = 60;
		double nsPerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int frames = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			timer += now - lastTime;
			lastTime = now;
			if (delta >= 1) {
				update();
				//ticks++;
				frames++;
				render();
				delta--;
			}

			if (timer >= 1000000000) {
				ticks = frames;
				frames = 0;
				timer = 0;
			}
		}
	}

	public void init() {
		handler = new Handler(this);
		gameFrame = new GameFrame(this, size);
		start();
	}

	public void update() {
		handler.update();
	}

	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, Launcher.WIDTH, Launcher.HEIGHT);
		handler.render(g2);
		g2.setColor(Color.cyan);
		g2.drawString(""+ticks, 10, 20);
		bs.show();
		g.dispose();
	}

	public static void main(String[] args) {
		new Launcher();
	}

	public int width() {
		return WIDTH;
	}

	public int height() {
		return HEIGHT;
	}

}
