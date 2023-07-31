package paintio.paintio;
import java.awt.Color;

public class ColoredRec{
    int x;
    int y;
    int width;
    int height;
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
    
    public int getHeight(){
    return height;
    }

    
    public ColoredRec(int x, int y,int height, int width, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

   /* public void draw(Graphics2D g2d, int tileSize) {
        g2d.setColor(color);
        g2d.fillRect(x * tileSize, y * tileSize, tileSize, width * tileSize);
    }*/
    
}
