
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {

    int width = 360;
    int height = 640;

    Logic logic;

    JLabel scoreLabel;

    public View(Logic logic) {

        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        // Mengatur layout
        setLayout(null);

        // Membuat JLabel skor
        scoreLabel = new JLabel("0");

        scoreLabel.setFont(
                new Font("Arial", Font.BOLD, 36)
        );

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        scoreLabel.setBounds(130, 20, 100, 50);

        add(scoreLabel);
    }

    // Memperbarui skor
    public void updateScore(int score) {

        scoreLabel.setText(String.valueOf(score));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {

        // Background
        g.drawImage(
                logic.getBackgroundImage(),
                0, 0, width, height, null
        );

        // Player
        Player player = logic.getPlayer();

        g.drawImage(
                player.getImage(),
                player.getPosX(),
                player.getPosY(),
                player.getWidth(),
                player.getHeight(),
                null
        );

        // Pipa
        ArrayList<Pipe> pipes = logic.getPipes();

        for (int i = 0; i < pipes.size(); i++) {

            Pipe pipe = pipes.get(i);

            g.drawImage(
                    pipe.getImage(),
                    pipe.getPosX(),
                    pipe.getPosY(),
                    pipe.getWidth(),
                    pipe.getHeight(),
                    null
            );
        }

        // Tampilan Game Over
        if (logic.isGameOver()) {

            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(0, 0, width, height);

            g2.setColor(Color.WHITE);

            g2.setFont(
                    new Font("Arial", Font.BOLD, 36)
            );

            String gameOverText = "GAME OVER";

            FontMetrics fm = g2.getFontMetrics();

            int textX = (width - fm.stringWidth(gameOverText)) / 2;

            g2.drawString(
                    gameOverText,
                    textX,
                    height / 2 - 20
            );

            g2.setFont(
                    new Font("Arial", Font.PLAIN, 18)
            );

            String restartText = "Press R to Restart";

            fm = g2.getFontMetrics();

            int restartX = (width - fm.stringWidth(restartText)) / 2;

            g2.drawString(
                    restartText,
                    restartX,
                    height / 2 + 25
            );
        }
    }
}
