package paintio;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ColoredRec{
    int x;
    int y;
    int width;
    Color color;
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getWidth(){
    return width;
    }

    
    public ColoredRec(int x, int y, int width, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.color = color;
    }

    public void draw(Graphics2D g2d, int tileSize) {
        g2d.setColor(color);
        g2d.fillRect(x * tileSize, y * tileSize, tileSize, width * tileSize);
    }
    
}