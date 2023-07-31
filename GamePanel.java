package paintio.paintio;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;



public class GamePanel extends JPanel implements Runnable {
    private LinkedList<Point> snake;
    private LinkedList<Point> path;
    private LinkedList<ColoredRec> coloredRectangles;
    private boolean useMouseControls = false;
    private boolean useKeyboardControls = false;
    private Clickhandler mouseIn;
    private ImageIcon gameOverImg;
    private WeaponA weapon; // Add this variable to hold the weapon object
    private ArrayList<Enemy> enemies;    
    private int originalTileSize = 16;
    private int scale = 3;
    private int tileSize = originalTileSize * scale;
    private boolean gameOver; // Flag to indicate if the game is over
    //private JButton restartButton;
    private boolean isOutsideBox;
    private int nextX;
    private int nextY;
    private int EboxX;
    private int EboxY;
    
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
   // public static final int TILE_SIZE = 20;
   // public static final int WIDTH = 800;
    //public static final int HEIGHT = 800;
    //public static final int GAME_SPEED = 100;
    private int cameraOffsetX; 
    private int cameraOffsetY; 

    int FPS  ; 
    private Keyhandler keyIn ;
    private Rectangle box ;
    private Point snakeHead;

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



    public GamePanel(int speedN, int enemyCount) {


        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        snake = new LinkedList<>();
        path = new LinkedList<>();
        coloredRectangles = new LinkedList<>();
        
        enemies = new ArrayList<>(enemyCount);
        for (int i = 0; i < enemyCount; i++) {
        generateEnemy();
        enemies.add(new Enemy(EboxX, EboxY));
        }
        weapon = new WeaponA(enemies,coloredRectangles);
        box = new Rectangle(boxX * tileSize, boxY * tileSize, boxSize * tileSize, boxSize * tileSize);
        isOutsideBox = false;
        mouseIn = new Clickhandler();
        keyIn = new Keyhandler();
        FPS = speedN;
        cameraOffsetX = 0;
        cameraOffsetY = 0;
        gameOverImg = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\GameOver.jpg") {};
        
        snake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));

        //make the snake move to the right direction initially
        keyIn.right = true; 
        mouseIn.right = true;


        this.addMouseListener(mouseIn);
        this.addKeyListener(keyIn);
        setFocusable(true);
      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


    for (Enemy enemyNum : enemies) {
        enemyNum.enemyDraw(g2d, tileSize, cameraOffsetX, cameraOffsetY);
    }
    // Draw the box
    g2d.setColor(Color.WHITE);
    g2d.fillRect(boxX * tileSize + cameraOffsetX, boxY * tileSize + cameraOffsetY, boxSize * tileSize, boxSize * tileSize);

    // Draw the path
    for (int i = 0; i < path.size(); i++) {
        Point segment = path.get(i);
        Color color = Color.WHITE;
        g2d.setColor(color);
        g2d.fillRect(segment.x * tileSize + cameraOffsetX, segment.y * tileSize + cameraOffsetY, tileSize, tileSize);
    }
    if (weapon.isShooting()) {
    weapon.weaponAdraw(g2d,snakeHead,tileSize,cameraOffsetX,cameraOffsetY);
    weapon.killIn3x3(snakeHead);

    }
    // Draw the colored rectangles from the coloredRectangles list
    for (ColoredRec coloredRectangle : coloredRectangles) {
        int x = coloredRectangle.getX();
        int y = coloredRectangle.getY();
        int width = coloredRectangle.getWidth();
        int height = coloredRectangle.getHeight();
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x * tileSize + cameraOffsetX, y * tileSize + cameraOffsetY, height * tileSize, width * tileSize);
    }
    
    
    
    

    // Draw the snake
    for (Point segment : snake) {
        g2d.setColor(Color.CYAN);
        int x = segment.x * tileSize + cameraOffsetX;
        int y = segment.y * tileSize + cameraOffsetY;
        g2d.fillRect(x, y, tileSize, tileSize);
    }

    // Draw "Game Over" message 
    if (gameOver) {
        gameOverImg.paintIcon(null, g2d, getWidth() / 2 - 300, getHeight() / 2 - 300);
      //  restartButton.setVisible(true); // Show the restart button
    }
    

    // Draw mouse controls if enabled
    if (useMouseControls) {
        mouseIn.drawArrows(g2d);
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
        if (gameOver ) return; // Return if the game is over
        // Move the snake in the current direction
        nextX = snake.getFirst().x;
        nextY = snake.getFirst().y;
        
        if (useKeyboardControls){
        String direction = keyIn.getLastDirection();
        weapon.setLastSnakeDirection(direction); // Set the last direction in the WeaponA class

        if(direction != null){
            switch (direction) {
            case "UP":
                nextY--;
                break;
            case "DOWN":
                nextY++;
                break;
            case "LEFT":
                nextX--;
                break;
            case "RIGHT":
                nextX++;
                break;
        }


        }
        if (keyIn.enter){
        weapon.shoot();
        keyIn.enter = false;
        }
        }
         if (useMouseControls) {
             String direction = keyIn.getLastDirection();
            weapon.setLastSnakeDirection(direction); // Set the last direction in the WeaponA class
             if(direction != null){
            switch (direction) {
            case "UP":
                nextY--;
                break;
            case "DOWN":
                nextY++;
                break;
            case "LEFT":
                nextX--;
                break;
            case "RIGHT":
                nextX++;
                break;
        }
        if (mouseIn.rightClick){
        weapon.shoot();
        mouseIn.rightClick = false;
        }
            
        }
        snakeHead = new Point(nextX, nextY);   
         
         cameraOffsetX = -nextX * tileSize + getWidth() / 2;
         cameraOffsetY = -nextY * tileSize + getHeight() / 2;
        if (path.contains(snakeHead)) {
            gameOver = true;
            return;
        }
      
        // Check if the snake is outside the box
         isOutsideBox = (nextX < boxX || nextX >= boxX + boxSize || nextY < boxY || nextY >= boxY + boxSize);

        if (isOutsideBox && !prevRects(snakeHead)) {
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
        
        for (Enemy enemyNum : enemies) {
        enemyNum.enemyUpdate();
    }
         for (int i =0 ; i < enemies.size() ; i++) {
             Enemy enemyIndex = enemies.get(i);
             if (enemyIndex.getEnemyPath().contains(snakeHead)){
                enemies.remove(i);
                enemyIndex.removeEnemy();
                i--;

        }
    }
       for (int i =0 ; i < enemies.size() ; i++) {
             Enemy enemyIndex = enemies.get(i);
             if (path.contains(enemyIndex.getEnemyHead())){
            gameOver = true;
            return;
        }
    }  
       checkEnemyCollisions();
       
       if (enemies.isEmpty()){
       gameOver = true;
       }
    }
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
        Color color = Color.WHITE; // Set the default color for rectangles
        if (path.contains(new Point(x,modY))&& !prevRects(new Point (x,modY))){
        ColoredRec coloredRectangle = new ColoredRec(x, y,1,1, color);
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
                ColoredRec coloredRectangle = new ColoredRec(x, y,1 ,modY - y, color);
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
        ColoredRec coloredRectangle = new ColoredRec(x, y,1,1, color);
        coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
        }
      else{
        for (int k = 0; k < maxY- minY; k++) {
            currentPoint = new Point(x, modY);
            Rectangle currentRect = new Rectangle(x * tileSize, modY * tileSize, tileSize, tileSize);
            if (!path.contains(currentPoint) && !box.intersects(currentRect) && !prevRects(currentPoint) ) {
                modY--;
            } else {
                ColoredRec coloredRectangle = new ColoredRec(x, modY,1, y-modY+1, color);
                coloredRectangles.addFirst(coloredRectangle); // Add to the new coloredRectangles list
               
                break;
            }

    }
        }
    }  
    }
    
    
    path.clear();
}
private boolean prevRects(Point point) {
    for (ColoredRec coloredRectangle : coloredRectangles) {
        int x = coloredRectangle.getX();
        int y = coloredRectangle.getY();
        int width = coloredRectangle.getWidth();
        int height = coloredRectangle.getHeight();

        // Check if the point lies inside the boundaries of the colored rectangle
        if (point.x >= x && point.x < x + height && point.y >= y && point.y < y + width) {
            return true;
        }
    }

    return false;
}
 public void generateEnemy(){
        while(true){
        EboxX = (int) (Math.random() * 50); 
        EboxY = (int) (Math.random() * 50); 
        if ((!(EboxX > 4 && EboxX < 15 && EboxY >4 && EboxY <15)) /*&& isPositionOccupied(boxX, boxY)*/){
            break;
        }
        }

 }
 /*private boolean isPositionOccupied(int EboxX,int EboxY){
        if (enemies.size()>1){
        this.EboxX = EboxX;
        this.EboxY = EboxY;
        
        for (int i = 1 ; i< enemies.size();i++){
        Enemy prevEsnake = enemies.get(i-1);
        Point prevEsnakeH = prevEsnake.getEnemyHead();
        if ((EboxX > prevEsnakeH.x+10 && EboxX < prevEsnakeH.x-10 && EboxY >prevEsnakeH.y+10 && EboxY <prevEsnakeH.y-10) && ){
        
            
            }
        }
}
 }*/
private void checkEnemyCollisions() {
    for (int i = 0; i < enemies.size(); i++) {
        Enemy enemy1 = enemies.get(i);
        Point enemy1H = enemy1.getEnemyHead();
        for (int j = i + 1; j < enemies.size(); j++) {
            Enemy enemy2 = enemies.get(j);
            if (enemy2.getEnemyPath().contains(enemy1H)) {
                enemies.remove(j);
                enemy2.removeEnemy(); // Clear enemy's path and reset its position
                j--; // Decrement j as the enemies list size is reduced
            }
        }
    }
}



// public void spawnNewEnemies(int enemtCount) {
//    for (int i = 0; i < numberOfNewEnemies; i++) {
//        enemies.add(new Enemy(/*Optional parameters for position and speed*/));
//    }
//}


//write the if statement to avoid using first loop
 //add color select for snake
//choose player color in menu
//snake and enemies paint over each other
//avoid enemies to spawn on each other
//if the new direction in the enemy clas != with previous, do it again
// create a winner screen
//adding a delay beetween guntrail and box creating
//when the enemy is inside its own box, its not being nlimnited
//should debug the weaponA fully
//add to readme: my scale of the game is *3 so I *3 every value you mentioned like weaponA distance
//replace prevrects with .contains
////add to readme: we dont have 189 degree rotation at this game
}
