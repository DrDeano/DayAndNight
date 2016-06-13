package game;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import main.ResourceLoader;

public class Sound {

	public static final Sound dayNNight = new Sound("DayNNite-HQ.wav");
	public static final Sound gunshot = new Sound("gunshot.wav");

	private Clip clip;
	private AudioInputStream sound;

	public Sound(String filePath) {
		try {
			clip = AudioSystem.getClip();
			sound = ResourceLoader.getSound(filePath);
			clip.open(sound);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}

	public void stop() {
		clip.stop();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
