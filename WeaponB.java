package paintio.paintio;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class WeaponB {
    private boolean isShooting;
    private LinkedList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Timer rechargeTimer;
    private boolean canShoot;
    private int bulletSize;
    private int bulletSpeed;
    private int maxBulletDistance;
    private ImageIcon bulletIcon;
    private final long rechargeTime; 

    public WeaponB(ArrayList<Enemy> enemies, int weaponBrecharge) {
        isShooting = false;
        this.enemies = enemies;
        rechargeTime = weaponBrecharge;
        bullets = new LinkedList<>();
        canShoot = true;
        bulletSize = 1;
        bulletSpeed = 2;
        maxBulletDistance = 1000;
        bulletIcon = new ImageIcon("C:\\Users\\SkySystem\\Documents\\NetBeansProjects\\paintIO\\src\\resources\\player\\bullet.png");
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

        Image bulletImage = bulletIcon.getImage();

        // Calculate rotation angle based on the snake's direction
        double rotationAngle = 0;
        switch (bullet.direction) {
            case "UP":
                rotationAngle = -Math.PI / 2;
                break;
            case "DOWN":
                rotationAngle = Math.PI / 2;
                break;
            case "LEFT":
                rotationAngle = Math.PI; 
                break;
            case "RIGHT":
                rotationAngle = 0;
                break;
        }
        AffineTransform rotationTransform = new AffineTransform();
        rotationTransform.rotate(rotationAngle, bullet.getX() * tileSize + cameraOffsetX + tileSize / 2, bullet.getY() * tileSize + cameraOffsetY + tileSize / 2);
        g2d.setTransform(rotationTransform);

        g2d.drawImage(bulletImage, bullet.getX() * tileSize + cameraOffsetX, bullet.getY() * tileSize + cameraOffsetY, bulletSize * tileSize, bulletSize * tileSize, null);

        g2d.setTransform(new AffineTransform());


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
