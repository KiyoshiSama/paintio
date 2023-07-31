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
    private int tileSize;
    private LinkedList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Timer rechargeTimer;
    private long rechargeInterval; // Time in milliseconds for recharging
    private boolean canShoot;
    private int bulletSize;
    private int bulletSpeed;
    private int maxBulletDistance; // Maximum distance a bullet can travel before being removed

    public WeaponB(ArrayList<Enemy> enemies) {
        isShooting = false;
        this.enemies = enemies;
        tileSize = 48;
        bullets = new LinkedList<>();
        rechargeInterval = 3000; // 3 seconds
        canShoot = true;
        bulletSize = 3;
        bulletSpeed = 5;
        maxBulletDistance = 1000; // Adjust this value as needed
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void shoot(Point snakeHead, String lastSnakeDirection) {
        if (canShoot) {
            isShooting = true;
            Bullet bullet = new Bullet(snakeHead, lastSnakeDirection);
            bullets.add(bullet);
            rechargeTimer = new Timer();
            rechargeTimer.schedule(new RechargeTask(), rechargeInterval);
        }
    }

    public void weaponAdraw(Graphics2D g2d, int cameraOffsetX, int cameraOffsetY) {
        g2d.setColor(Color.BLACK);
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            bullet.move(bulletSpeed);

            int x = bullet.getX() * tileSize + cameraOffsetX;
            int y = bullet.getY() * tileSize + cameraOffsetY;
            g2d.fillRect(x, y, tileSize, tileSize);

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

        for (Enemy enemy : enemies) {
            if (enemy.getEnemyHead().equals(bullet.getPosition())) {
                hitEnemy = true;
                enemy.removeEnemy();
                break;
            }
        }

        return hitEnemy;
    }

    private class RechargeTask extends TimerTask {
        @Override
        public void run() {
            canShoot = true;
            isShooting = false;
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
                    position.translate(0, -speed);
                    break;
                case "DOWN":
                    position.translate(0, speed);
                    break;
                case "LEFT":
                    position.translate(-speed, 0);
                    break;
                case "RIGHT":
                    position.translate(speed, 0);
                    break;
            }
            distance += speed;
        }
    }
}
