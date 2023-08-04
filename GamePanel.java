package paintio.paintio;

import javax.swing.JPanel;
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
    private WeaponA weapon;
    private WeaponB weapon2;
    private ArrayList<Enemy> enemies;    
    private int originalTileSize = 16;
    private int scale = 3;
    private int tileSize = originalTileSize * scale;
    private boolean gameOver;
    private boolean isFirstEnemyAdded = false;
    private boolean isOutsideBox;
    private int nextX;
    private int nextY;
    private int enemyNumber;
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    private int cameraOffsetX; 
    private int cameraOffsetY; 
    private int EboxX;
    private int EboxY;
    private int FPS  ; 
    private Keyhandler keyIn ;
    private Rectangle box ;
    private Point snakeHead;
    private int enemiesSpeed;
    private ImageIcon snakeHeadIcon;
    private ImageIcon pathIcon;
    private Color fillColor;

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



    public GamePanel(int speedN, int enemyCount,int weaponAammo,int weaponBrecharge,int enemiesSpeed,String character) {


        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        this.enemiesSpeed = enemiesSpeed;
        snake = new LinkedList<>();
        path = new LinkedList<>();
        coloredRectangles = new LinkedList<>();
        box = new Rectangle(boxX * tileSize, boxY * tileSize, boxSize * tileSize, boxSize * tileSize);
        enemies = new ArrayList<>(enemyCount);
        enemyNumber=0;
        weapon = new WeaponA(enemies,coloredRectangles,weaponAammo);
        weapon2 = new WeaponB(enemies,weaponBrecharge);
        isOutsideBox = false;
        mouseIn = new Clickhandler();
        keyIn = new Keyhandler();
        FPS = speedN;
        cameraOffsetX = 0;
        cameraOffsetY = 0;
        gameOverImg = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\GameOver.jpg");
        visualSelect(character);
        snake.add(new Point(boxX + boxSize / 2, boxY + boxSize / 2));
        //make the snake move to the right direction initially
        keyIn.right = true; 
        mouseIn.right = true;

        this.addMouseListener(mouseIn);
        this.addKeyListener(keyIn);
        setFocusable(true);
        spawningEnemies(enemyCount);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


    for (Enemy enemyNum : enemies) {
        enemyNum.enemyDraw(g2d, tileSize, cameraOffsetX, cameraOffsetY);
    }
    // Draw the box
    g2d.setColor(fillColor);
    g2d.fillRect(boxX * tileSize + cameraOffsetX, boxY * tileSize + cameraOffsetY, boxSize * tileSize, boxSize * tileSize);

    // Draw the path
    for (int i = 0; i < path.size(); i++) {
        Point segment = path.get(i);
        int x = segment.x * tileSize + cameraOffsetX;
        int y =  segment.y * tileSize + cameraOffsetY;
        pathIcon.paintIcon(this, g2d, x, y);
    }
    if (weapon.isShooting()) {
    weapon.weaponAdraw(g2d,snakeHead,tileSize,cameraOffsetX,cameraOffsetY);
    weapon.killIn3x3();

    }
    if (weapon2.isShooting()) {
        weapon2.weaponBdraw(g2d, tileSize, cameraOffsetX, cameraOffsetY);
    }
    // Draw the colored rectangles from the coloredRectangles list
     for (ColoredRec coloredRectangle : coloredRectangles) {
        int x = coloredRectangle.getX();
        int y = coloredRectangle.getY();
        int width = coloredRectangle.getWidth();
        int height = coloredRectangle.getHeight();

        g2d.setColor(fillColor);
        g2d.fillRect(x * tileSize + cameraOffsetX, y * tileSize + cameraOffsetY, height * tileSize, width * tileSize);
    }
    
//    // Draw the snake
//    for (Point segment : snake) {
//        g2d.setColor(Color.CYAN);
//        int x = segment.x * tileSize + cameraOffsetX;
//        int y = segment.y * tileSize + cameraOffsetY;
//        g2d.fillRect(x, y, tileSize, tileSize);
//    }
    if (!snake.isEmpty()) {
        Point head = snake.getFirst();
        int x = head.x * tileSize + cameraOffsetX;
        int y = head.y * tileSize + cameraOffsetY;
        snakeHeadIcon.paintIcon(this, g2d, x, y);
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
        if(keyIn.space){
        weapon2.shoot(snakeHead, direction);
        keyIn.space = false;
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
        if(mouseIn.leftClick){
        weapon2.shoot(snakeHead, direction);
        mouseIn.leftClick = false;
        }   
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
        
         for (int i =0 ; i < enemies.size() ; i++){
         Enemy enemyIndex = enemies.get(i);
         if (snakeHead.equals(enemyIndex.getEnemyHead())){
         gameOver=true;
         return;
         }
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
private void visualSelect(String character){
    switch(character){
        case "BATMAN":
            snakeHeadIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\BatmanInGame.png");
            pathIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\Fill\\batPath.png");
            fillColor = Color.BLACK;
            break;
        case "PENNY":
            snakeHeadIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\PennyInMenu.png");
            pathIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\Fill\\pennyPath.png");
            fillColor = Color.RED;
            break;
        case "RICK":
            snakeHeadIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\RickInGame.png");
            pathIcon = new ImageIcon ("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\Fill\\rickpath.png");
            fillColor = Color.GREEN;
            break;
        case "WALTER":
            snakeHeadIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\WalterInGame.png");
            pathIcon = new ImageIcon ("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\paintio\\paintio\\Pics\\Fill\\walterPath.png");
            fillColor = Color.WHITE;
            break;
    }

}
private void spawningEnemies(int enemyCount) {
    for (int i = 0; i < enemyCount; i++) {
        generateEnemy();
    }
}


public void generateEnemy() {

    if (!isFirstEnemyAdded) {
        while (true){
        EboxX = (int) (Math.random() * 50);
        EboxY = (int) (Math.random() * 50);
        int distance = (int) Math.sqrt((EboxX- boxX) * (EboxX - boxX) + (EboxY - boxY) * (EboxY - boxY));
        if (distance>30) {
            break;
        }
        }
        enemies.add(new Enemy(EboxX, EboxY,enemiesSpeed));
        isFirstEnemyAdded = true;
        
    } else {
        while (true) {
            EboxX = (int) (Math.random() * 50);
            EboxY = (int) (Math.random() * 50);
            if (isPositionFarFromOthers(EboxX, EboxY)) {
                break;
            }
        }
        enemyNumber++;
        enemies.add(new Enemy(EboxX, EboxY,enemiesSpeed));
    }
}


 private boolean isPositionFarFromOthers(int x, int y) {
     
    for (int i = 0 ; i < enemyNumber+1 ;i++) {
        Enemy currentEnemy = enemies.get(i);
        Point enemyHead = currentEnemy.getEnemyHead();
        int oldDistance = (int) Math.sqrt((x- boxX) * (x - boxX) + (y - boxY) * (y - boxY));
        int newDistance = (int) Math.sqrt((x - enemyHead.x) * (x - enemyHead.x) + (y - enemyHead.y) * (y - enemyHead.y));
        if (newDistance < 20 && oldDistance < 30) {
            return false;
        }
    }
    return true;
}


private void checkEnemyCollisions() {
    for (int i = 0; i < enemies.size(); i++) {
        Enemy enemy1 = enemies.get(i);
        Point enemy1H = enemy1.getEnemyHead();
        for (int j = i + 1; j < enemies.size(); j++) {
            Enemy enemy2 = enemies.get(j);
            if (enemy2.getEnemyPath().contains(enemy1H)) {
                enemies.remove(j);
                enemy2.removeEnemy(); 
                j--; 
            }
        }
    }
}


 //add color select for snake
//snake and enemies paint over each other
//if the new direction in the enemy clas != with previous, do it again
// create a winner screen
//adding a delay beetween guntrail and box creating
//when the enemy is inside its own box, its not being nlimnited
//should debug the weaponA fully
//add to readme: my scale of the game is *3 so I *3 every value you mentioned like weaponA distance
//replace prevrects with .contains
////add to readme: we dont have 189 degree rotation at this game
//showing ammo counts
//mouse movement doesn't work
//add to readme : key hints
//arrows for mouse movements doesn't work
//adding texture
//changing the movement algorithm
//add to readme: bullet size are 1 by default and it is hard to hit the enemies, if you want to check it works correctly, change ots value
}
