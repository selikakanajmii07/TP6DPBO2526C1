import javax.sound.sampled.*;
import java.net.URL;

public class AudioManager {

    private Clip flapSound;
    private Clip scoreSound;
    private Clip hitSound;
    private Clip gameOverSound;

    public AudioManager() {

        flapSound = loadSound("/assets/flap.wav");
        scoreSound = loadSound("/assets/score.wav");
        hitSound = loadSound("/assets/hit.wav");
        gameOverSound = loadSound("/assets/gameover.wav");
    }

    // Memuat file audio
    private Clip loadSound(String path) {

        try {

            URL url = getClass().getResource(path);

            if (url == null) {
                System.out.println("Audio tidak ditemukan: " + path);
                return null;
            }

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(url);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            audioStream.close();

            return clip;

        } catch (Exception e) {

            System.out.println("Gagal memuat audio: " + path);
            e.printStackTrace();

            return null;
        }
    }

    // Memainkan suara
    private void playSound(Clip clip) {

        if (clip != null) {

            if (clip.isRunning()) {
                clip.stop();
            }

            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void playFlap() {
        playSound(flapSound);
    }

    public void playScore() {
        playSound(scoreSound);
    }

    public void playHit() {
        playSound(hitSound);
    }

    public void playGameOver() {
        playSound(gameOverSound);
    }
}

