package paintio.paintio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Enemy {
    
    private int originalTileSize = 16;
    private int scale = 3;
    private int tileSize = originalTileSize * scale;
    private int speedN;
    private LinkedList<Point> Esnake;
    private LinkedList<Point> path;
    private LinkedList<ColoredRec> coloredRectangles;
    private LinkedList<Color> pathColors; 
    private LinkedList<LinkedList<Point>> previousPaths;
    private boolean isOutsideBox;
    boolean isValidMove;
    private int boxX;
    private int boxY;
    private int boxSize;
    private int nextX;
    private int nextY;
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    private Point EsnakeHead;
    private Rectangle box ;
    private Color[] enemyColors ;
    private Color enemyColor;
    private Color enemyHcolor;

    public Enemy(/*int boxX , int boxY*/) {
        //this.boxX = boxX;
        //this.boxY = boxY;
        Esnake = new LinkedList<>();
        path = new LinkedList<>();
        coloredRectangles = new LinkedList<>();
        pathColors = new LinkedList<>();
        previousPaths = new LinkedList<>();
        box = new Rectangle(boxX * tileSize, boxY * tileSize, boxSize * tileSize, boxSize * tileSize);
        while(true){
        boxX = (int) (Math.random() * 30); 
        boxY = (int) (Math.random() * 30); 
        if (!(boxX > 7 && boxX < 16 && boxY >7 && boxY <16))break;
        }
        boxSize = 6;
        randomColor();
        enemyColors = randomColor();
        enemyColor = enemyColors[0];
        enemyHcolor = enemyColors[1];
        Esnake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));
    }

    public void enemyDraw(Graphics2D g2d, int tileSize, int cameraOffsetX, int cameraOffsetY) {
        
        // Draw the box
        g2d.setColor(enemyColor);
        g2d.fillRect(boxX * tileSize + cameraOffsetX, boxY * tileSize + cameraOffsetY, boxSize * tileSize, boxSize * tileSize);
        
        // Draw the path and store the colors in pathColors
        for (int i = 0; i < path.size(); i++) {
            Point segment = path.get(i);
            g2d.setColor(enemyColor);
            g2d.fillRect(segment.x * tileSize + cameraOffsetX, segment.y * tileSize + cameraOffsetY, tileSize, tileSize);
            pathColors.add(enemyColor); // Store the color in pathColors
    }

        // Draw the colored rectangles
        for (ColoredRec coloredRectangle : coloredRectangles) {
            int recX = coloredRectangle.getX();
            int recY = coloredRectangle.getY();
            int width = coloredRectangle.getWidth();
            g2d.setColor(enemyColor);
            g2d.fillRect(recX * tileSize + cameraOffsetX, recY * tileSize + cameraOffsetY, tileSize, width * tileSize);
        }
        // Draw the Esnake
        for (Point segment : Esnake) {
        g2d.setColor(enemyHcolor);
        int EsnakeX = segment.x * tileSize + cameraOffsetX;
        int EsnakeY = segment.y * tileSize + cameraOffsetY;
        g2d.fillRect(EsnakeX, EsnakeY, tileSize, tileSize);
    }
    }

        public void enemyUpdate() {
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
        ColoredRec coloredRectangle = new ColoredRec(x, y,1, color);
        coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
        }
        else{
        for (int k = 0; k < maxY- minY; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect) && !prevRects(currentPoint) ) {
                modY++;
            } else {
                modY++;
                ColoredRec coloredRectangle = new ColoredRec(x, y, modY - y, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
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
        ColoredRec coloredRectangle = new ColoredRec(x, y,1, color);
        coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
        }
      else{
        for (int k = 0; k < maxY- minY; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect) && !prevRects(currentPoint) ) {
                modY--;
            } else {
                ColoredRec coloredRectangle = new ColoredRec(x, modY, y-modY+1, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
                break;
            }

    }
        }
    }  
    }
    
    
    path.clear();
}
        
        public void restartEnemy() {
        Esnake.clear();
        path.clear();
        coloredRectangles.clear();
        Esnake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));
        Color[] newEnemyColors = randomColor();
        enemyColor = newEnemyColors[0];
        enemyHcolor = newEnemyColors[1];
    }
        
           
            
        

}
