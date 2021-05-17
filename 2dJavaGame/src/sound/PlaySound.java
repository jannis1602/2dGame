package sound;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import object.Bullet;

public class PlaySound implements Runnable {
	URL audioFile;
	static Clip clip;
	public boolean playsound = false;
	Thread thread;

	public PlaySound() {

	}

	public void run() {
		System.out.println("run");
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
			clip.open(ais);
			clip.start();
			long totalFrames = (long) (clip.getFrameLength() / clip.getFormat().getFrameRate());
			System.out.println(">länge " + totalFrames);
//			if (totalFrames <= 1) {
//				totalFrames = 1;
//				System.out.println(">>neue länge " + totalFrames);
//			}
//			while (clip.getFramePosition() != clip.getFrameLength() && playsound) {
//			}
//			clip.close();
			System.out.println(">ENDE clip");
		} catch (Exception e) {
		}
	}

	public void play(URL audio) {
		System.out.println("play");
		this.audioFile = audio;
//		this.audioFile=PlaySound.class.getResource("setsound.wav");
		playsound = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stopPlay() {
		playsound = false;
	}
}
