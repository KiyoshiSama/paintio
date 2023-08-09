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

    
}
