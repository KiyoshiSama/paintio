
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
    private Enemy enemy;
    private ArrayList<Enemy> enemies;
    private Keyhandler keyIn;
    private String lastSnakeDirection;
    private int bulletStartPosX;
    private int bulletStartPosY ;
    private float redOpacity;//Initial opacity value



    public WeaponA(ArrayList<Enemy> enemies) {
        ammo = 5;
        isShooting = false;
        this.enemies = enemies;
        keyIn = new Keyhandler();
        redOpacity =0.5f ;
        
    

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

    public void reset() {
        ammo = 5;
        isShooting = false;
    }

    public int getAmmo() {
        return ammo;
    }
    
    private boolean isWithin3x3Area(int x, int y, int centerX, int centerY) {
        return Math.abs(x - centerX) <= 1 && Math.abs(y - centerY) <= 1;
    }
    
    public String getDirection() {
    if (keyIn.up) {
        return "UP";
    } else if (keyIn.down) {
        return "DOWN";
    } else if (keyIn.left) {
        return "LEFT";
    } else if (keyIn.right) {
        return "RIGHT";
    }
    return null;
}

    public void eliminateEnemiesIn3x3Area(Point centerPoint) {
    for (int i = 0; i < enemies.size(); i++) {
        Enemy enemyIndex = enemies.get(i);
        Point enemyHead = enemyIndex.getEnemyHead();
        if (isWithin3x3Area(enemyHead.x, enemyHead.y, centerPoint.x, centerPoint.y)) {
            enemies.remove(i);
            enemyIndex.removeEnemy();
            i--;
        } else {
            LinkedList<Point> enemyPath = enemyIndex.getEnemyPath();
            for (int j = 0; j < enemyPath.size(); j++) {
                Point pathPoint = enemyPath.get(j);
                if (isWithin3x3Area(pathPoint.x, pathPoint.y, centerPoint.x, centerPoint.y)) {
                    enemies.remove(j);
                    enemyIndex.removeEnemy();
                    break;
                }
            }
        }
    }
}
    public void weaponAdraw(Graphics2D g2d,Point snakeHead,int tileSize,int cameraOffsetX,int cameraOffsetY){
        

    if (lastSnakeDirection != null) {
        int boxOffsetX = 0;
        int boxOffsetY = 0;
  
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

        // Draw the 3x3 box
        g2d.setColor(Color.WHITE);
        int weaponAsize = 3;
        g2d.fillRect((snakeHead.x + boxOffsetX -1) * tileSize + cameraOffsetX , (snakeHead.y + boxOffsetY-1) * tileSize + cameraOffsetY, weaponAsize * tileSize, weaponAsize * tileSize);
        
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
    public void setRedOpacity(float opacity) {
        redOpacity = Math.max(0.0f, Math.min(1.0f, opacity));
    }

}




