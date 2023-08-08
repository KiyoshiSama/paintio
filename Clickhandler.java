package paintio.paintio;


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
    public boolean rightClick,leftClick;
    private ImageIcon arrowUpImage;
    private ImageIcon arrowDownImage;
    private ImageIcon arrowLeftImage;
    private ImageIcon arrowRightImage;
    public String lastDirection = "RIGHT";


    

    public Clickhandler() {
        arrowUpImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\resources\\arrows\\U.png");
        arrowDownImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\resources\\arrows\\D.png");
        arrowLeftImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\resources\\arrows\\L.png");
        arrowRightImage = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\resources\\arrows\\R.png");
        arrowUpRect = new Rectangle(1600, 500, arrowUpImage.getIconWidth(), arrowUpImage.getIconHeight());
        arrowDownRect = new Rectangle(1600, 700, arrowDownImage.getIconWidth(), arrowDownImage.getIconHeight());
        arrowLeftRect = new Rectangle(1515, 600, arrowLeftImage.getIconWidth(), arrowLeftImage.getIconHeight());
        arrowRightRect = new Rectangle(1680, 600, arrowRightImage.getIconWidth(), arrowRightImage.getIconHeight());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mousePoint = e.getPoint();
        int click = e.getButton();
        up = false;
        down = false;
        left = false;
        right = false;
        rightClick = false; 
        leftClick = false;
        if (arrowUpRect.contains(mousePoint)) {
            up = true;
            lastDirection = "UP";
        } else if (arrowDownRect.contains(mousePoint)) {
            down = true;
            lastDirection = "DOWN";
        } else if (arrowLeftRect.contains(mousePoint)) {
            left = true;
            lastDirection = "LEFT";
        } else if (arrowRightRect.contains(mousePoint)) {
            right = true; 
            lastDirection = "RIGHT";
        }
        else if (click == MouseEvent.BUTTON3) {
                    rightClick = true;
        }
        else if (click == MouseEvent.BUTTON1) {
                    leftClick = true;
        }
        else{
        up = false;
        down = false;
        left = false;
        right = false;
        rightClick = false;
        leftClick = false;
        }
        
         
    }
    public String getLastDirection() {
        return lastDirection;
    }
    public void drawArrows(Graphics2D g2d) {
        // Draw the arrows using the Graphics2D object
        arrowUpImage.paintIcon(null, g2d, arrowUpRect.x, arrowUpRect.y);
        arrowDownImage.paintIcon(null, g2d, arrowDownRect.x, arrowDownRect.y);
        arrowLeftImage.paintIcon(null, g2d, arrowLeftRect.x, arrowLeftRect.y);
        arrowRightImage.paintIcon(null, g2d, arrowRightRect.x, arrowRightRect.y);
    }


        


    
}

