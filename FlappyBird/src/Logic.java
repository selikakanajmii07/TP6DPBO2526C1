
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener {

    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeWidth = 64;
    int pipeHeight = 512;

    int gravity = 1;
    int pipeVelocityX = -2;

    Timer gameLoop;
    Timer pipesCooldown;

    View view;

    // Audio
    AudioManager audio = new AudioManager();

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    Player player;

    ArrayList<Pipe> pipes;
    ArrayList<Pipe> scoredPipes;

    boolean gameStarted = false;
    boolean gameOver = false;

    int score = 0;

    public Logic() {

        backgroundImage = new ImageIcon(
                getClass().getResource("/assets/background.png")
        ).getImage();

        birdImage = new ImageIcon(
                getClass().getResource("/assets/bird.png")
        ).getImage();

        lowerPipeImage = new ImageIcon(
                getClass().getResource("/assets/lowerPipe.png")
        ).getImage();

        upperPipeImage = new ImageIcon(
                getClass().getResource("/assets/upperPipe.png")
        ).getImage();

        player = new Player(
                playerStartPosX,
                playerStartPosY,
                playerWidth,
                playerHeight,
                birdImage
        );

        pipes = new ArrayList<>();
        scoredPipes = new ArrayList<>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    // Tombol SPACE untuk terbang
    public void flap() {

        if (gameOver) {
            return;
        }

        gameStarted = true;

        player.setVelocityY(-9);

        // Suara burung terbang
        audio.playFlap();

        if (!pipesCooldown.isRunning()) {
            pipesCooldown.start();
        }
    }

    // Membuat pipa
    public void placePipes() {

        int randomPipeY = (int) (
                -100 - Math.random() * 200
        );

        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(
                pipeStartPosX,
                randomPipeY,
                pipeWidth,
                pipeHeight,
                upperPipeImage
        );

        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(
                pipeStartPosX,
                randomPipeY + pipeHeight + openingSpace,
                pipeWidth,
                pipeHeight,
                lowerPipeImage
        );

        pipes.add(lowerPipe);
    }

    // Menggerakkan burung dan pipa
    public void move() {

        if (!gameStarted || gameOver) {
            return;
        }

        // Gravitasi burung
        player.setVelocityY(
                player.getVelocityY() + gravity
        );

        player.setPosY(
                player.getPosY() + player.getVelocityY()
        );

        // Mengecek batas bawah layar
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            endGame();
            return;
        }

        // Menggerakkan pipa
        for (int i = 0; i < pipes.size(); i++) {

            Pipe pipe = pipes.get(i);

            pipe.setPosX(
                    pipe.getPosX() + pipeVelocityX
            );
        }

        // Mengecek tabrakan
        Rectangle playerRect = new Rectangle(
                player.getPosX(),
                player.getPosY(),
                player.getWidth(),
                player.getHeight()
        );

        for (int i = 0; i < pipes.size(); i++) {

            Pipe pipe = pipes.get(i);

            Rectangle pipeRect = new Rectangle(
                    pipe.getPosX(),
                    pipe.getPosY(),
                    pipe.getWidth(),
                    pipe.getHeight()
            );

            if (playerRect.intersects(pipeRect)) {
                endGame();
                return;
            }
        }

        // Menghitung skor ketika melewati pipa atas
        for (int i = 0; i < pipes.size(); i += 2) {

            Pipe upperPipe = pipes.get(i);

            if (upperPipe.getPosX() + upperPipe.getWidth()
                    < player.getPosX()
                    && !scoredPipes.contains(upperPipe)) {

                score++;

                // Suara mendapatkan skor
                audio.playScore();

                scoredPipes.add(upperPipe);

                if (view != null) {
                    view.updateScore(score);
                }
            }
        }

        // Menghapus pipa yang keluar layar
        for (int i = 0; i < pipes.size(); i++) {

            Pipe pipe = pipes.get(i);

            if (pipe.getPosX() + pipe.getWidth() < 0) {

                scoredPipes.remove(pipe);

                pipes.remove(i);

                i--;
            }
        }
    }

    // Game Over
    public void endGame() {

        if (gameOver) {
            return;
        }

        gameOver = true;

        pipesCooldown.stop();

        // Suara tabrakan
        audio.playHit();

        // Suara Game Over setelah tabrakan
        Timer delay = new Timer(350, e -> {
            audio.playGameOver();
        });

        delay.setRepeats(false);
        delay.start();
    }

    // Restart permainan
    public void restart() {

        if (!gameOver) {
            return;
        }

        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipes.clear();
        scoredPipes.clear();

        score = 0;

        gameStarted = false;
        gameOver = false;

        pipesCooldown.stop();

        if (view != null) {
            view.updateScore(score);
            view.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        move();

        if (view != null) {
            view.repaint();
        }
    }
}
