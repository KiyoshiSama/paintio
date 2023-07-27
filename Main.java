package paintio;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("paint.io Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);

            // Create a GamePanel with the desired dimensions
            GamePanel gamePanel = new GamePanel();

            frame.add(gamePanel);

            frame.pack();
            frame.setVisible(true);

            gamePanel.startGameThread();

        });
    }
}
