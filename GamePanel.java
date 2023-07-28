package paintio.paintio;

import paintio.paintio.Clickhandler;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.LinkedList;


public class GamePanel extends JPanel implements Runnable {
    private LinkedList<Point> snake;
    private LinkedList<Point> path;
    private LinkedList<ColoredRec> coloredRectangles;
    private LinkedList<Color> pathColors; 
    private LinkedList<LinkedList<Point>> previousPaths;
    private boolean useMouseControls = false;
    private boolean useKeyboardControls = false;
    private Clickhandler mouseIn;
        private ImageIcon gameOverImg;
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    private boolean gameOver; // Flag to indicate if the game is over
    private JButton restartButton;
    boolean isOutsideBox;
    int nextX;
    int nextY;
    int modY;
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    public static final int TILE_SIZE = 20;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int GAME_SPEED = 100;
    private int size ;

    private Enemy enemy;
    private ArrayList<Enemy> enemies;
    int FPS ; // FPS
    private Keyhandler keyIn ;
    private Rectangle box ;
    Point snakeHead;

    final int boxSize = 9; 
    final int boxX = 6; // X-coordinate of the box's top-left corner
    final int boxY = 6; // Y-coordinate of the box's top-left corner

    Thread gameThread;


    public void setUseMouseControls(boolean useMouseControls) {
        this.useMouseControls = useMouseControls;
    }
    public void setUseKeyboardControls(boolean useKeyboardControls) {
        this.useKeyboardControls = useKeyboardControls;
    }



    public GamePanel(int speedN) {


        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        snake = new LinkedList<>();
        path = new LinkedList<>();
        pathColors = new LinkedList<>();
        coloredRectangles = new LinkedList<>();
        previousPaths = new LinkedList<>();
        enemies = new ArrayList<>();
        box = new Rectangle(boxX * tileSize, boxY * tileSize, boxSize * tileSize, boxSize * tileSize);
        size = path.size();
        isOutsideBox = false;
        mouseIn = new Clickhandler();
        keyIn = new Keyhandler();
        modY=0;
        FPS = speedN;
        gameOverImg = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\GameOver.jpg") {};
        
        snake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));
        //this.enemy = new Enemy(getRandomCoordinate(GRID_WIDTH), getRandomCoordinate(GRID_HEIGHT), 20, 20);

        //make the snake move to the right direction initially
        keyIn.right = true; 
        mouseIn.right = true;
        

        this.addKeyListener(keyIn);
        this.addMouseListener(mouseIn);
        this.addKeyListener(keyIn);
        setFocusable(true);
       restartButton = new JButton("Restart");
        restartButton.setBounds(200 , 200, 100, 50);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        restartButton.setVisible(false);
        this.add(restartButton);
            
      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the box
        g2d.setColor(Color.WHITE);
        g2d.fillRect(boxX * tileSize, boxY * tileSize, boxSize * tileSize, boxSize * tileSize);


        // Draw the path and store the colors in pathColors

        for (int i = 0; i < path.size(); i++) {
            Point segment = path.get(i);
            Color color = Color.WHITE;
            g2d.setColor(color);
            g2d.fillRect(segment.x * tileSize, segment.y * tileSize, tileSize, tileSize);
            pathColors.add(color); // Store the color in pathColors
        }
            
            // Draw the colored rectangles from the coloredRectangles list
        for (ColoredRec coloredRectangle : coloredRectangles) {
            coloredRectangle.draw(g2d, tileSize);
        }
                // Draw the snake
        for (Point segment : snake) {
            g2d.setColor(Color.CYAN);
            g2d.fillRect(segment.x * tileSize, segment.y * tileSize, tileSize, tileSize);
        }
       for (Enemy enemy : enemies) {
    enemy.draw(g);
}

         if (useMouseControls) {
            mouseIn.drawArrows(g2d);
        }
         if (gameOver) {
            // Draw "Game Over" message
            gameOverImg.paintIcon(null, g2d, getWidth() / 2 - 300, getHeight() / 2  - 300);
            restartButton.setVisible(true); // Show the restart button
        }

        
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                for (Enemy enemy : enemies) {
                enemy.move();
                // Other enemy behavior update code...
                 }
               // checkCollision();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameOver) return; // Return if the game is over
        // Move the snake in the current direction
        nextX = snake.getFirst().x;
        nextY = snake.getFirst().y;
        snakeHead = new Point(nextX , nextY);
        if (!path.isEmpty())rectIntersect(snakeHead);    
        if (useKeyboardControls){
        if (keyIn.left) {
            nextX--;
        } else if (keyIn.right) {
            nextX++;
        } else if (keyIn.up) {
            nextY--;
        } else if (keyIn.down) {
            nextY++;
        }}
         if (useMouseControls) {
            if (mouseIn.up) {
                nextY--;
            } else if (mouseIn.down) {
                 nextY++;
            } else if (mouseIn.left) {
                nextX--;
            } else if (mouseIn.right) {
                nextX++;
            }
        }
        Point nextPosition = new Point(nextX, nextY);
        if (path.contains(nextPosition)) {
            gameOver = true;
            restartButton.setVisible(true); // Show the restart button
            return;
        }
      
        // Check if the snake is outside the box
         isOutsideBox = (nextX < boxX || nextX >= boxX + boxSize || nextY < boxY || nextY >= boxY + boxSize);

        if (isOutsideBox) {
            Point coordinate = new Point(nextX, nextY);
             path.addFirst(coordinate);
      } else {
            if (!path.isEmpty()) {
                fillBlock();
             } 
         }

        // Move the snake's head
        snake.addFirst(new Point(nextX, nextY));
        snake.removeLast();

    }



private void fillBlock() {
    for (Point point : path) {
    int x = point.x;
    int y = point.y;

    minX = Math.min(minX, x);
    minY = Math.min(minY, y);
    maxX = Math.max(maxX, x);
    maxY = Math.max(maxY, y);
}

    for (int i = path.size() - 1; i >= 0; i--) {
        int x = path.get(i).x;
        int y = path.get(i).y;
        
        modY = y + 1;
        Point currentPoint;
        //if (currentPoint.equals(path.getLast()))break;
        Color color = Color.WHITE; // Set the default color for rectangles
        for (int k = 0; k < maxX- minX; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect)) {
                modY++;
            } else {
                modY++;
                ColoredRec coloredRectangle = new ColoredRec(x, y, modY - y, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
                break;
            }
        }
        
    }
    for (int i = path.size()-1; i >= 0; i--) {
        int x = path.get(i).x;
        int y = path.get(i).y;
        
        modY = y - 1;
        Color color = Color.WHITE; // Set the default color for rectangles
        Point currentPoint;
        for (int k = 0; k < maxX- minX; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect)) {
                modY--;
            } else {
               // modY--;
                ColoredRec coloredRectangle = new ColoredRec(x, y+1, modY - y, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
                break;
            }
        }
        
    }
    
    
    previousPaths.add(new LinkedList<>(path));
    
    
    path.clear();
}
 
private void rectIntersect(Point head) {
    for (ColoredRec currentRec : coloredRectangles) {
        if (head.equals(new Point(currentRec.getX(), currentRec.getY()))) {
            fillnewBlock();
            break;
        }
    }
}
private void fillnewBlock(){
        previousPaths.add(new LinkedList<>(path));

    for (Point point : path) {
    int x = point.x;
    int y = point.y;

    minX = Math.min(minX, x);
    minY = Math.min(minY, y);
    maxX = Math.max(maxX, x);
    maxY = Math.max(maxY, y);
}

    for (int i = path.size() - 1; i >= 0; i--) {
        int x = path.get(i).x;
        int y = path.get(i).y;
        
        modY = y + 1;
        Color color = Color.WHITE; // Set the default color for rectangles
        for (int k = 0; k < maxX- minX; k++) {
            Point currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!prevPaths(currentPoint) && !box.intersects(currentRect)) {
                modY++;
            } else {
                modY++;
                ColoredRec coloredRectangle = new ColoredRec(x, y, modY - y, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
                break;
            }
        }
        
    }
    for (int i = path.size()-1; i >= 0; i--) {
        int x = path.get(i).x;
        int y = path.get(i).y;
        
        modY = y - 1;
        Color color = Color.WHITE; // Set the default color for rectangles
        Point currentPoint;
        for (int k = 0; k < maxX- minX; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!prevRects(currentPoint)) {
                modY--;
            } else {
               /* if (currentRect.intersects(box) || prevPaths(currentPoint)) {
                    break;
                }*/
                ColoredRec coloredRectangle = new ColoredRec(x, y, modY - y, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
                break;
            }
        }
        
    }
        
    path.clear();


}
private boolean prevPaths(Point point) {
    for (LinkedList<Point> prevPath : previousPaths) {
        if (prevPath.contains(point)) {
            return true;
        }
    }
    return false;
}
private boolean prevRects(Point point) {
    for (ColoredRec coloredRectangle : coloredRectangles) {
        int x = coloredRectangle.getX();
        int y = coloredRectangle.getY();
        int width = coloredRectangle.getWidth();

        // Check if the point lies inside the boundaries of the colored rectangle
        if (point.x >= x && point.x < x + 9 && point.y >= y && point.y < y + width) {
            return true;
        }
    }

    return false;
}

 public void restartGame() {
        gameOver = false;
        snake.clear();
        path.clear();
        coloredRectangles.clear();
        snake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));
        keyIn.right = true;
        restartButton.setVisible(false); // Hide the restart button again
    }



//write the if statement to avoid using first loop
//stop adding useless path when snake hits the border of new painted rec when its inside
 //add color select for snake

}
