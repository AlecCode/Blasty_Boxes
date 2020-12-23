package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
    private Clip sound;

    // MODIFIES: this
    // EFFECTS: loads an audio file
    public Audio(String f) {
        try {
            File file = new File(f);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            this.sound = AudioSystem.getClip();
            this.sound.open(ais);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // EFFECTS: plays the audio file continuously
    public void loop() {
        this.sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // EFFECTS: plays the audio clip
    public void play() {
        this.sound.setFramePosition(0);
        this.sound.start();
    }

    // EFFECTS: pauses and unpauses the audio clip
    public void togglePause() {
        if (this.sound.isRunning()) {
            this.sound.stop();
        } else {
            this.sound.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
