import java.awt.*;

public class ObstacleTwo {

    private int x;
    private int y;
    private int width;
    private int hight;
    private Rectangle box;

    public void paint (Graphics graphics){
        graphics.setColor(Color.cyan);
        graphics.fillRect(x,y,width,hight);
    }


    public ObstacleTwo(int x, int y, int width, int hight) {
        box= new Rectangle();
        box.setBounds(x,y,width,hight);
        this.x = x;
        this.y = y;
        this.width = width;
        this.hight = hight;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHight() {
        return hight;
    }

    public Rectangle getBox() {
        return box;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

}


