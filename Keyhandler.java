
package paintio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener {
    public boolean up, left, down, right ;

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
        if (code == KeyEvent.VK_LEFT) {
            left = true;
        } else if (code == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (code == KeyEvent.VK_UP) {
            up = true;
        } else if (code == KeyEvent.VK_DOWN) {
            down = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        }
    }
    

