package paintio.paintio;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and show the input selection frame
            GameMenu menuFrame = new GameMenu();
            menuFrame.setVisible(true);


        });
    }
}

