package paintio.paintio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;


public class Clickhandler extends MouseAdapter {
    private Rectangle arrowUpRect;
    private Rectangle arrowDownRect;
    private Rectangle arrowLeftRect;
    private Rectangle arrowRightRect;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    private ImageIcon arrowUpImage;
    private ImageIcon arrowDownImage;
    private ImageIcon arrowLeftImage;
    private ImageIcon arrowRightImage;
    public Clickhandler() {
        arrowUpImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\U.png");
        arrowDownImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\D.png");
        arrowLeftImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\L.png");
        arrowRightImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\R.png");
        // Initialize the mouse arrow rectangle bounds (adjust coordinates as needed)
        arrowUpRect = new Rectangle(1600, 500, arrowUpImage.getIconWidth(), arrowUpImage.getIconHeight());
        arrowDownRect = new Rectangle(1600, 700, arrowDownImage.getIconWidth(), arrowDownImage.getIconHeight());
        arrowLeftRect = new Rectangle(1450, 630, arrowLeftImage.getIconWidth(), arrowLeftImage.getIconHeight());
        arrowRightRect = new Rectangle(1680, 630, arrowRightImage.getIconWidth(), arrowRightImage.getIconHeight());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mousePoint = e.getPoint();
        
        up = false;
        down = false;
        left = false;
        right = false;
        if (arrowUpRect.contains(mousePoint)) {
            up = true;

        } else if (arrowDownRect.contains(mousePoint)) {
            down = true;
        } else if (arrowLeftRect.contains(mousePoint)) {
            left = true;
        } else if (arrowRightRect.contains(mousePoint)) {
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


        


    
}

