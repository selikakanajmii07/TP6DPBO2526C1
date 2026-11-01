
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {

    public static void main(String[] args) {

        JFrame frame = new JFrame("FlappyBird");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Membuat CardLayout untuk berpindah halaman
        CardLayout cardLayout = new CardLayout();

        JPanel mainPanel = new JPanel(cardLayout);

        // Membuat Logic dan View game
        Logic logika = new Logic();
        View tampilan = new View(logika);

        logika.setView(tampilan);

        // Membuat halaman Main Menu
        MainMenu menu = new MainMenu(
                // Ketika tombol PLAY ditekan
                () -> {
                    cardLayout.show(mainPanel, "GAME");
                    tampilan.requestFocusInWindow();
                },

                // Ketika tombol EXIT ditekan
                () -> {
                    System.exit(0);
                }
        );

        // Memasukkan halaman ke CardLayout
        mainPanel.add(menu, "MENU");
        mainPanel.add(tampilan, "GAME");

        frame.add(mainPanel);

        // Tombol SPACE untuk terbang
        JRootPane root = frame.getRootPane();

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(
                        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
                        "flap"
                );

        root.getActionMap().put("flap", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tampilan.isShowing()) {
                    logika.flap();
                }
            }
        });

        // Tombol R untuk restart
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(
                        KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),
                        "restart"
                );

        root.getActionMap().put("restart", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tampilan.isShowing()) {
                    logika.restart();
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
