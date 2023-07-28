package paintio.paintio;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Enemy {
    private int x;
    private int y;
    private int width;
    private int height;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction enemyDirection;

    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enemyDirection = getRandomDirection();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    private Direction getRandomDirection() {
        Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    public void move() {
        int dx = 0;
        int dy = 0;

        // Calculate the current grid position of the enemy
        int gridX = x / GamePanel.TILE_SIZE;
        int gridY = y / GamePanel.TILE_SIZE;

        // Randomly change direction if the enemy reaches the boundary of the current grid
        if (gridX <= 0 || gridX >= GamePanel.WIDTH / GamePanel.TILE_SIZE || gridY <= 0 || gridY >= GamePanel.HEIGHT / GamePanel.TILE_SIZE) {
            enemyDirection = getRandomDirection();
        }

        // Update the position based on the current direction
        switch (enemyDirection) {
            case UP:
                dy = -GamePanel.TILE_SIZE;
                break;
            case DOWN:
                dy = GamePanel.TILE_SIZE;
                break;
            case LEFT:
                dx = -GamePanel.TILE_SIZE;
                break;
            case RIGHT:
                dx = GamePanel.TILE_SIZE;
                break;
        }

        x += dx;
        y += dy;
    }
}
