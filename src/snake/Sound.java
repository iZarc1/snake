/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import javax.sound.sampled.*;
import java.io.*;

/**
 *
 * @author izaelb
 */

public class Sound {

    private static Clip backgroundClip;

    public static void play(String filename) {
        new Thread(() -> {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(
                    Sound.class.getResourceAsStream("/sounds/" + filename)
                );
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void playBackground(String filename) {
        try {
            stopBackground(); // para cualquier música anterior
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                Sound.class.getResourceAsStream("/sounds/" + filename)
            );
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audio);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // loop infinito
            backgroundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackground() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }

    public static void pauseBackground() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    public static void resumeBackground() {
        if (backgroundClip != null && !backgroundClip.isRunning()) {
            backgroundClip.start();
        }
    }
}
