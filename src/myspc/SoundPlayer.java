package myspc;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class SoundPlayer {
    
    public void sound(String soundPath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "ERRORE NELLA RIPRODUZIONE DELL'AUDIO! ", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
