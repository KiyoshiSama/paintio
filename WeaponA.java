
package paintio.paintio;

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


    public WeaponA(ArrayList<Enemy> enemies) {
        ammo = 5;
        isShooting = false;
        this.enemies = enemies;
                keyIn = new Keyhandler();

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

}


