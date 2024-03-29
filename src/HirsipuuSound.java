
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class HirsipuuSound {

    Clip clip;
    AudioInputStream sound;

    public HirsipuuSound() {

    }

    public void setSound() {
        try {

            File file = new File("src/Ääniefektit/dsdead.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
        } catch (Exception e) {

        }
    }

    public void setWinsound() {
        try {

            File file = new File("src/Ääniefektit/win.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-35.0f);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}
