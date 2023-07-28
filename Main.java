package paintio;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and show the input selection frame
            InputSelect inputFrame = new InputSelect();
            inputFrame.setVisible(true);

         
             if (inputFrame.isInputSelected()){
            
            boolean useMouseControls = inputFrame.isUseMouseControls();
            boolean useKeyboardControls = inputFrame.isUseKeyboardControls();

             inputFrame.dispose();
         
            // Now create and show the game frame with the chosen input mode
            JFrame frame = new JFrame();
            frame.setTitle("paint.io Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);

            // Create a GamePanel with the desired dimensions
            GamePanel gamePanel = new GamePanel();

            // Set the chosen input mode in the GamePanel
            gamePanel.setUseMouseControls(useMouseControls);
            gamePanel.setUseKeyboardControls(useKeyboardControls);

            frame.add(gamePanel);

            frame.pack();
            frame.setVisible(true);

            gamePanel.startGameThread();
             }
        });
    }
}