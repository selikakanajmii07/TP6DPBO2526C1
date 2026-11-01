import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    JButton playButton;
    JButton exitButton;

    Image backgroundImage;

    public MainMenu(Runnable onPlay, Runnable onExit) {

        setPreferredSize(new Dimension(360, 640));
        setLayout(null);

        // Memuat background
        backgroundImage = new ImageIcon(
                getClass().getResource("/assets/background.png")
        ).getImage();

        // Judul game
        JLabel title = new JLabel("FLAPPY BIRD");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(20, 180, 320, 60);

        add(title);

        // Tombol PLAY
        playButton = new JButton("PLAY");
        playButton.setFont(new Font("Arial", Font.BOLD, 22));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(40, 170, 40));
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setBounds(90, 300, 180, 55);

        playButton.addActionListener(e -> onPlay.run());

        add(playButton);

        // Tombol EXIT
        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Arial", Font.BOLD, 22));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(200, 60, 50));
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setBounds(90, 380, 180, 55);

        exitButton.addActionListener(e -> onExit.run());

        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Menggambar background
        g.drawImage(
                backgroundImage,
                0, 0,
                getWidth(),
                getHeight(),
                this
        );

        // Lapisan gelap agar tulisan terlihat
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

