package paintio.paintio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import paintio.ColoredRec;

public class Clickhandler extends MouseAdapter {
    private Rectangle arrowUpRect;
    private Rectangle arrowDownRect;
    private Rectangle arrowLeftRect;
    private Rectangle arrowRightRect;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private ImageIcon arrowUpImage;
    private ImageIcon arrowDownImage;
    private ImageIcon arrowLeftImage;
    private ImageIcon arrowRightImage;
    public Clickhandler() {
        arrowUpImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\U.png");
        arrowDownImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\D.png");
        arrowLeftImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\L.png");
        arrowRightImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\R.png");
        // Initialize the mouse arrow rectangle bounds (adjust coordinates as needed)
        arrowUpRect = new Rectangle(1600, 500, arrowUpImage.getIconWidth(), arrowUpImage.getIconHeight());
        arrowDownRect = new Rectangle(1600, 700, arrowDownImage.getIconWidth(), arrowDownImage.getIconHeight());
        arrowLeftRect = new Rectangle(1450, 630, arrowLeftImage.getIconWidth(), arrowLeftImage.getIconHeight());
        arrowRightRect = new Rectangle(1680, 630, arrowRightImage.getIconWidth(), arrowRightImage.getIconHeight());

        up = false;
        down = false;
        left = false;
        right = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mousePoint = e.getPoint();
        if (arrowUpRect.contains(mousePoint)) {
            up = true;
            down = false;
            left = false;
            right = false;
        } else if (arrowDownRect.contains(mousePoint)) {
            up = false;
            down = true;
            left = false;
            right = false;
        } else if (arrowLeftRect.contains(mousePoint)) {
            up = false;
            down = false;
            left = true;
            right = false;
        } else if (arrowRightRect.contains(mousePoint)) {
            up = false;
            down = false;
            left = false;
            right = true;
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }
    public void drawArrows(Graphics2D g2d) {
        // Draw the arrows using the Graphics2D object
        arrowUpImage.paintIcon(null, g2d, arrowUpRect.x, arrowUpRect.y);
        arrowDownImage.paintIcon(null, g2d, arrowDownRect.x, arrowDownRect.y);
        arrowLeftImage.paintIcon(null, g2d, arrowLeftRect.x, arrowLeftRect.y);
        arrowRightImage.paintIcon(null, g2d, arrowRightRect.x, arrowRightRect.y);
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

        


    
}

