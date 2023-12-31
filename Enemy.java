package paintio.paintio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class Enemy {
    
    
    private LinkedList<Point> Esnake;
    private LinkedList<Point> path;
    private LinkedList<ColoredRec> coloredRectangles;
    private Set<Integer> generatedNumbers;
    private boolean isOutsideBox;
    private boolean isValidMove;
    private boolean canChangeMove;
    private int boxX;
    private int boxY;
    private int boxSize = 9;
    private int nextX;
    private int nextY;
    private int originalTileSize = 16;
    private int scale = 3;
    private int tileSize = originalTileSize * scale;
    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int maxY = Integer.MIN_VALUE;
    private Point EsnakeHead;
    private Rectangle box ;
    private Color[] enemyColors ;
    private Color enemyColor;
    private Timer directionTimer;
    private long rechargeTime; 
    private ImageIcon headIcon;
    private ImageIcon pathIcon;

    


    public Enemy(int boxX, int boxY,int enemiesSpeed) {
        this.boxX = boxX;
        this.boxY= boxY;
        canChangeMove = true;
        Esnake = new LinkedList<>();
        rechargeTime = enemiesSpeed;
        path = new LinkedList<>();
        generatedNumbers = new HashSet<>();
        coloredRectangles = new LinkedList<>();
        box = new Rectangle(boxX * tileSize, boxY * tileSize, boxSize * tileSize, boxSize * tileSize);
        directionTimer = new Timer();
        randomColor();
        enemyColors = randomColor();
        enemyColor = enemyColors[0];
        Esnake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));
        visualSelect();
    }
        public LinkedList<Point> getEnemyPath() {
        return path;
    }
    public Point getEnemyHead(){
    return Esnake.getFirst();
    }
     private class DirectionChangeTask extends TimerTask {
        @Override
        public void run() {
            canChangeMove = true;

        }
    }

    public void enemyDraw(Graphics2D g2d, int tileSize, int cameraOffsetX, int cameraOffsetY) {
        
        // Draw the box
        g2d.setColor(enemyColor);
        g2d.fillRect(boxX * tileSize + cameraOffsetX, boxY * tileSize + cameraOffsetY, boxSize * tileSize, boxSize * tileSize);
        
        // Draw the path and store the colors in pathColors
        for (int i = 0; i < path.size(); i++) {
        Point segment = path.get(i);
        int x = segment.x * tileSize + cameraOffsetX;
        int y =  segment.y * tileSize + cameraOffsetY;
        pathIcon.paintIcon(null, g2d, x, y);
    }

        // Draw the colored rectangles
        for (ColoredRec coloredRectangle : coloredRectangles) {
            int recX = coloredRectangle.getX();
            int recY = coloredRectangle.getY();
            int width = coloredRectangle.getWidth();
            int height = coloredRectangle.getHeight();
            g2d.setColor(enemyColor);
            g2d.fillRect(recX * tileSize + cameraOffsetX, recY * tileSize + cameraOffsetY, height * tileSize, width * tileSize);
        }
        // Draw the Esnake
        if (!Esnake.isEmpty()) {
        Point head = Esnake.getFirst();
        int x = head.x * tileSize + cameraOffsetX;
        int y = head.y * tileSize + cameraOffsetY;
        headIcon.paintIcon(null, g2d, x, y);
    
    }
    }

        public void enemyUpdate() {
            if(canChangeMove){
            nextX = Esnake.getFirst().x;
            nextY = Esnake.getFirst().y;
            isValidMove = false;
            double randomValue = Math.random() ;
            // Adjust the probability of different movement directions by changing the weights
            double[] movementWeights = {0.25, 0.25, 0.25, 0.25}; // Up, Down, Left, Right
            
            while (!isValidMove){
                if (randomValue < movementWeights[0]) { // Up
                    nextY--;
                    
                 } else if (randomValue < movementWeights[0] + movementWeights[1]) { // Down
                    nextY++;
                    
                } else if (randomValue < movementWeights[0] + movementWeights[1] + movementWeights[2]) { // Left
                    nextX--;
                   
                } else { // Right
                    nextX++;
                    
                }
            
                EsnakeHead = new Point(nextX,nextY);
                 // Check if the new position intersects with the enemy's own path
                if (!path.contains(EsnakeHead)) {
                    isValidMove = true;
                    }
                
                }
                isOutsideBox = (nextX < boxX || nextX >= boxX + boxSize || nextY < boxY || nextY >= boxY + boxSize);
                
            if (isOutsideBox ) {
                Point coordinate = new Point(nextX, nextY);
                path.addFirst(coordinate);
                } else {
                if (!path.isEmpty()) {
                    fillBlock();
                    } 
                }

                 // Move the snake's head
                Esnake.addFirst(new Point(nextX, nextY));
                Esnake.removeLast();
                canChangeMove = false;
                directionTimer.schedule(new DirectionChangeTask(), rechargeTime);

            }

            }
        
        private boolean prevRects(Point point) {
            for (ColoredRec coloredRectangle : coloredRectangles) {
                int x = coloredRectangle.getX();
                int y = coloredRectangle.getY();
                int width = coloredRectangle.getWidth();
                int height = coloredRectangle.getHeight();

                // Check if the point lies inside the boundaries of the colored rectangle
            if (point.x >= x && point.x < x + height && point.y >= y && point.y < y + width) { //**mayy cause problems**
                return true;
                }
            }
            return false;
            }
        public Color[] randomColor() {
            Color[] randomColors = new Color[2];
                for (int i = 0; i < 2; i++) {
                    int red = (int) (Math.random() * 256);
                    int green = (int) (Math.random() * 256);
                    int blue = (int) (Math.random() * 256);
                    randomColors[i] = new Color(red, green, blue);
                }
                return randomColors;
            
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
        
        int modY = y + 1;
        Point currentPoint;
        Color color = Color.WHITE;
        if (path.contains(new Point(x,modY))&& !prevRects(new Point (x,modY))){
        ColoredRec coloredRectangle = new ColoredRec(x, y,1,1, color);
        coloredRectangles.addFirst(coloredRectangle); 
        }
        else{
        for (int k = 0; k < maxY- minY; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect) && !prevRects(currentPoint) ) {
                modY++;
            } else {
                modY++;
                ColoredRec coloredRectangle = new ColoredRec(x, y,1 ,modY - y, color);
                coloredRectangles.addFirst(coloredRectangle); 
               
                break;
            }
        }
    }
        
    }
    
    for (int i = 0; i <= path.size()-1; i++) {
        int x = path.get(i).x;
        int y = path.get(i).y;
        if (!prevRects(path.get(i))){
        int modY = y - 1;
        Color color = Color.WHITE; // Set the default color for rectangles
        Point currentPoint;
      if (path.contains(new Point(x,modY))){
        ColoredRec coloredRectangle = new ColoredRec(x, y,1,1, color);
        coloredRectangles.addFirst(coloredRectangle); 
        }
      else{
        for (int k = 0; k < maxY- minY; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect) && !prevRects(currentPoint) ) {
                modY--;
            } else {
                ColoredRec coloredRectangle = new ColoredRec(x, modY,1, y-modY+1, color);
                coloredRectangles.addFirst(coloredRectangle); 
               
                break;
            }

    }
        }
    }  
    }    
    path.clear();
  
}

        public void visualSelect(){
        
       Random random = new Random();
        int maxNumber = 10; // highset number
            int randomNumber;

        do {
        randomNumber = random.nextInt(maxNumber)+1 ;
        } while (generatedNumbers.contains(randomNumber));
        
        generatedNumbers.add(randomNumber);
        
        switch (randomNumber) {
    case 1:
        headIcon = new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\anonymous.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\chip.png");
        break;
    case 2:
        headIcon = new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\alien.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\moonphase.png");
        break;
    case 3:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\dali.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\money.png");
        break;
    case 4:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\darthvader.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\crown.png");
        break;
    case 5:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\jason.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\blood.png");
        break;
    case 6:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\joker.png");
        pathIcon = new ImageIcon ("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\resources\\EnemiesFill\\spades.png");
        break;
    case 7:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\morty.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\summer.png");
        break;
    case 8:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\scream.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\knife.png");
        break;
    case 9:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\starks.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\star.png");
        break;
    case 10:
        headIcon =new ImageIcon("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\Enemies\\supermario.png");
        pathIcon = new ImageIcon ("C:\\Users\\ASUS\\Desktop\\java\\paintio\\src\\paintio\\paintio\\resources\\EnemiesFill\\diamond.png");
        break;
}
       
        
        
        }

       
        public void removeEnemy() {
        Esnake.clear();
        path.clear();
        coloredRectangles.clear();
        directionTimer.cancel();

    }
        
           
            
        

}
