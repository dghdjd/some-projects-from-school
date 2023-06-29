package ui;

import sun.audio.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * play the sound file with given file name in the data file
 */

public class Music {



    public Music(String sound) {
        AudioPlayer player = AudioPlayer.player;
        AudioStream soundEffect = null;

        try {
            soundEffect = new AudioStream(new FileInputStream("./data/" + sound + ".wav"));
        } catch (IOException ex) {
            //
        }

        player.start(soundEffect);

    }


}
