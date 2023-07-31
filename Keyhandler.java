
package paintio.paintio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener {
    public boolean up, left, down, right , enter,space ;
    public String lastDirection = "RIGHT";

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        up = false;
        down = false;
        right = false;
        left = false;
        enter = false;
        switch (code) {
            case KeyEvent.VK_LEFT:
                left = true;
                lastDirection = "LEFT";
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                lastDirection = "RIGHT";
                break;
            case KeyEvent.VK_UP:
                up = true;
                lastDirection = "UP";
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                lastDirection = "DOWN";
                break;
            default:
                break;
        }
        if (code == KeyEvent.VK_ENTER )enter = true;
        if (code == KeyEvent.VK_ENTER )space = true;

    }
    public String getLastDirection() {
        return lastDirection;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        }
    }
    

