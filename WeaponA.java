
package paintio.paintio;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;


public class WeaponA {
    private int ammo;
    private boolean isShooting;
    private int tileSize;
    private ArrayList<Enemy> enemies;
    private Keyhandler keyIn;
    private String lastSnakeDirection;
    private int bulletStartPosX;
    private int bulletStartPosY ;
    private float redOpacity;//Initial opacity value
    private int AboxX;
    private int AboxY;
    private LinkedList<ColoredRec> coloredRectangles;
    private int boxOffsetX;
    private int boxOffsetY;


    public WeaponA(ArrayList<Enemy> enemies,LinkedList<ColoredRec> coloredRectangles) {
        ammo = 5;
        isShooting = false;
        this.enemies = enemies;
        keyIn = new Keyhandler();
        tileSize = 48;
        redOpacity =0.5f ;
        this.coloredRectangles = coloredRectangles;
        
        
    

    }
    public void setLastSnakeDirection(String lastSnakeDirection) {
    this.lastSnakeDirection = lastSnakeDirection;
}


    public boolean isShooting() {
        return isShooting;
    }

    public void shoot() {
        if (!isShooting && ammo > 0) {
        isShooting = true;
        ammo--;
    }
    }


    public int getAmmo() {
        return ammo;
    }
    
    public void setRedOpacity(float opacity) {
        redOpacity = Math.max(0.0f, Math.min(1.0f, opacity));
    }
    
    public void weaponAdraw(Graphics2D g2d,Point snakeHead,int tileSize,int cameraOffsetX,int cameraOffsetY){
        
    if (lastSnakeDirection != null) {
         boxOffsetX = 0;
         boxOffsetY = 0;
        AboxX = 0;
        AboxY= 0;
  
        switch (lastSnakeDirection) {
            case "UP":
                boxOffsetY = -5;
                AboxX = bulletStartPosX;
                AboxY = bulletStartPosY-(3*tileSize);
                break;
            case "DOWN":
                boxOffsetY = 5;
                AboxX = bulletStartPosX;
                AboxY = bulletStartPosY;
                break;
            case "LEFT":
                boxOffsetX = -5;
                AboxX = bulletStartPosX-(3*tileSize);
                AboxY = bulletStartPosY;
                break;
            case "RIGHT":
                boxOffsetX = +5;
                AboxX = bulletStartPosX;
                AboxY = bulletStartPosY;
                break;
        }

        // Draw the 3x3 box
        g2d.setColor(Color.WHITE);
        int weaponAsize = 3;
        g2d.fillRect((snakeHead.x + boxOffsetX -1) * tileSize + cameraOffsetX , (snakeHead.y + boxOffsetY-1) * tileSize + cameraOffsetY, weaponAsize * tileSize, weaponAsize * tileSize);
//        ColoredRec coloredRectangle = new ColoredRec((snakeHead.x + boxOffsetX -1)* tileSize,(snakeHead.y + boxOffsetY-1)*tileSize ,  3, 3, Color.WHITE);
//        coloredRectangles.addFirst(coloredRectangle);
        bulletStartPosX = snakeHead.x * tileSize + cameraOffsetX;
        bulletStartPosY = snakeHead.y * tileSize + cameraOffsetY;
        isShooting = false;


    }
    }
    public void drawBulletTrail(Graphics2D g2d,int tileSize) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, redOpacity));
            g2d.setColor(new Color(1.0f, 0.0f, 0.0f, redOpacity)); // Set the color with alpha value

            switch (lastSnakeDirection) {
            case "UP":
                g2d.fillRect(bulletStartPosX, bulletStartPosY-(3*tileSize), tileSize, 4*tileSize);
                break;
            case "DOWN":
                g2d.fillRect(bulletStartPosX, bulletStartPosY , tileSize, 4*tileSize);
                break;
            case "LEFT":
                g2d.fillRect(bulletStartPosX-(3*tileSize), bulletStartPosY, 4*tileSize, tileSize);
                break;
            case "RIGHT":
                g2d.fillRect(bulletStartPosX, bulletStartPosY, 4*tileSize, tileSize);
                break;
        }
        
    }
    
    private boolean isWithin3x3Area(Point enemyHead,Point snakeHead) {
        int weaopnAboxSize = 3;
//        int AboxHeight = AboxX + weaopnAboxSize;
//        int AboxWidth = AboxY + weaopnAboxSize;
        return enemyHead.x >= (snakeHead.x + boxOffsetX -1) * tileSize && enemyHead.x < (snakeHead.x + boxOffsetX -1) * tileSize + weaopnAboxSize*tileSize  && enemyHead.y >= (snakeHead.y + boxOffsetY-1) * tileSize  && enemyHead.y < (snakeHead.y + boxOffsetY-1) * tileSize + weaopnAboxSize * tileSize ;
 
        
    }
    public void killIn3x3(Point snakeHead) {
    ArrayList<Enemy> modEnemies = new ArrayList<>();
    for (int i = 0; i < enemies.size(); i++) {
        Enemy enemyIndex = enemies.get(i);
        Point enemyHead = enemyIndex.getEnemyHead();
        if (isWithin3x3Area(enemyHead,snakeHead)) {
            enemies.remove(i);
            modEnemies.add(enemyIndex);
           // i--;
        } else {
            LinkedList<Point> enemyPath = enemyIndex.getEnemyPath();
            for (int j = 0; j < enemyPath.size(); j++) {
                Point pathPoint = enemyPath.get(j);
                if (isWithin3x3Area(pathPoint,snakeHead)) {
                    enemies.remove(j);
                    modEnemies.add(enemyIndex);
                    break;
                }
            }
        }
    }
    enemies.removeAll(modEnemies);
    for (Enemy enemyToRemove : modEnemies) {
        enemyToRemove.removeEnemy();
//        ColoredRec coloredRectangle = new ColoredRec((snakeHead.x + boxOffsetX -1) * tileSize ,(snakeHead.y + boxOffsetY -1) * tileSize ,3,3,Color.WHITE);
//        coloredRectangles.add(coloredRectangle);
    }
    
}

}




