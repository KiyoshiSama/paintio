
package paintio.paintio;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;


public class WeaponA {
    private int ammo;
    private boolean isShooting;
    private int tileSize;
    private ArrayList<Enemy> enemies;
    private Keyhandler keyIn;
    private String lastSnakeDirection= "RIGHT";
    private int bulletStartPosX;
    private int bulletStartPosY ;
    private float redOpacity;//Initial opacity value
    private LinkedList<ColoredRec> coloredRectangles;
    private int boxOffsetX;
    private int boxOffsetY;
    private Rectangle Abox;
    private int weaponAsize;
    private int newBoxX;
    private int newBoxY;

    public WeaponA(ArrayList<Enemy> enemies,LinkedList<ColoredRec> coloredRectangles) {
        ammo = 5;
        isShooting = false;
        this.enemies = enemies;
        keyIn = new Keyhandler();
        tileSize = 48;
        weaponAsize =3;
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
        newBoxX=0;
        newBoxY=0;
  
        switch (lastSnakeDirection) {
            case "UP":
                boxOffsetY = -5;
                break;
            case "DOWN":
                boxOffsetY = 5;
                break;
            case "LEFT":
                boxOffsetX = -5;
                break;
            case "RIGHT":
                boxOffsetX = +5;
                break;
        }
        newBoxX= (snakeHead.x + boxOffsetX -1);
        newBoxY = (snakeHead.y + boxOffsetY-1) ;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(newBoxX * tileSize + cameraOffsetX ,newBoxY * tileSize + cameraOffsetY, weaponAsize * tileSize, weaponAsize * tileSize);
        ColoredRec coloredRectangle = new ColoredRec(newBoxX,newBoxY, 3, 3, Color.WHITE);
        coloredRectangles.addFirst(coloredRectangle);
        bulletStartPosX = snakeHead.x * tileSize + cameraOffsetX;
        bulletStartPosY = snakeHead.y * tileSize + cameraOffsetY;
        isShooting = false;
        drawBulletTrail(g2d, tileSize);
          g2d.setColor(Color.WHITE);
    

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
    
    private boolean isWithin3x3Area(Point enemyHead,Point pathPoint, LinkedList<Point> pathPoints) {
         Abox = new Rectangle(newBoxX * tileSize ,newBoxY * tileSize , weaponAsize * tileSize, weaponAsize * tileSize);
         if (Abox.contains(enemyHead)) {
        return true;
    }
         if (pathPoint != null){
        for (Point PP : pathPoints) {
            if (Abox.contains(pathPoint)) {
                return true;
            }
        }
    }

    return false;
}
    
    public void killIn3x3(Point snakeHead) {
    ArrayList<Enemy> modEnemies = new ArrayList<>();
    for (int i = 0; i < enemies.size(); i++) {
        Point pathPoint = null ;
        Enemy enemyIndex = enemies.get(i);
        Point enemyHead = enemyIndex.getEnemyHead();
        LinkedList<Point> pathPoints = enemyIndex.getEnemyPath();
        if (isWithin3x3Area(enemyHead, pathPoint, pathPoints)) {
            enemies.remove(i);
            modEnemies.add(enemyIndex);
        } else {
            for (int j = 0; j < pathPoints.size(); j++) {
                pathPoint = pathPoints.get(j);
                if (isWithin3x3Area(enemyHead, pathPoint, pathPoints)) {
                    enemies.remove(i);
                    modEnemies.add(enemyIndex);
                    break;
                }
            }
        }
    }
    enemies.removeAll(modEnemies);
    for (Enemy enemyToRemove : modEnemies) {
        enemyToRemove.removeEnemy();
    }
}


}




