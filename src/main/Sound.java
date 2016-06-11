package main;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sound {
	
	public static final Sound slurp = new Sound("slurping.wav");
	public static final Sound type = new Sound("typing.wav");
	
	Clip clip;

	public Sound(String filePath) {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream sound = ResourceLoader.getSound(filePath);
			clip.open(sound);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void play(){
		clip.start();
	}
	
	public void stop(){
		clip.stop();
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
