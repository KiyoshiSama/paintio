package paintio.paintio;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class WeaponB {
    private boolean isShooting;
    private LinkedList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Timer rechargeTimer;
    private boolean canShoot;
    private int bulletSize;
    private int bulletSpeed;
    private int maxBulletDistance;
    private final long rechargeTime = 3000; 

    public WeaponB(ArrayList<Enemy> enemies) {
        isShooting = false;
        this.enemies = enemies;

        bullets = new LinkedList<>();
        canShoot = true;
        bulletSize = 1;
        bulletSpeed = 2;
        maxBulletDistance = 1000; 
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void shoot(Point snakeHead, String lastSnakeDirection) {
        if (canShoot) {
            isShooting = true;
            Bullet bullet = new Bullet(new Point(snakeHead), lastSnakeDirection);
            bullets.add(bullet);
            canShoot = false;
            rechargeTimer = new Timer();
            rechargeTimer.schedule(new RechargeTask(), rechargeTime);
        }
    }

    public void weaponBdraw(Graphics2D g2d,int tileSize, int cameraOffsetX, int cameraOffsetY) {
        g2d.setColor(Color.BLUE);
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            bullet.move(bulletSpeed);

            g2d.fillRect(bullet.getX() * tileSize + cameraOffsetX, bullet.getY() * tileSize + cameraOffsetY, bulletSize *tileSize, bulletSize*tileSize);

            if (bullet.getDistance() > maxBulletDistance) {
                bulletsToRemove.add(bullet);
            }

            boolean hitEnemy = checkForEnemyCollisions(bullet);
            if (hitEnemy) {
                bulletsToRemove.add(bullet);
            }
        }

        bullets.removeAll(bulletsToRemove);
    }

    private boolean checkForEnemyCollisions(Bullet bullet) {
    boolean hitEnemy = false;
    Rectangle Bbox = new Rectangle(bullet.getX() ,bullet.getY(), bulletSize, bulletSize);
    for (int i = 0; i < enemies.size(); i++) {
        Enemy enemy = enemies.get(i);
        Point enemyHead = enemy.getEnemyHead();

        if (Bbox.contains(enemyHead)) {
            hitEnemy = true;
            enemies.remove(i);
            break;
        }
    }

    return hitEnemy;
}


    private class RechargeTask extends TimerTask {
        @Override
        public void run() {
            canShoot = true; // Recharge is over, enable shooting again
            rechargeTimer.cancel();
            rechargeTimer.purge();
        }
        
    }

    private static class Bullet {
        private Point position;
        private String direction;
        private int distance;

        public Bullet(Point position, String direction) {
            this.position = new Point(position);
            this.direction = direction;
            this.distance = 0;
        }

        public Point getPosition() {
            return position;
        }

        public int getX() {
            return position.x;
        }

        public int getY() {
            return position.y;
        }

        public int getDistance() {
            return distance;
        }

        public void move(int speed) {
            switch (direction) {
                case "UP":
                    position.y -= speed;
                    break;
                case "DOWN":
                    position.y += speed;
                    break;
                case "LEFT":
                    position.x -= speed;
                    break;
                case "RIGHT":
                   position.x += speed;
                   
                    break;
            }
            distance += speed;
        }
    }
}
