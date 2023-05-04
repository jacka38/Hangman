package src;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sound {

    Clip clip;
    AudioInputStream sound;


    public Sound(){

    }


    public void setSound(){

        try{
  
            File file = new File("src/dsdead.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
        }catch(Exception e){
  
        }
     }

     public void setWinsound(){
        try{
  
            File file = new File("src/win.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        }catch(Exception e){
  
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
