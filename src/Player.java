import javax.swing.*;
import java.awt.*;

public class Player {
    private ImageIcon icon;
    private int x;
    private int y;
    private int width;
    private int hight;
    private boolean alive;

    public Player(int x, int y) {
        this.icon = new ImageIcon("image/bird.png");
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    public void paint(Graphics graphics, GameScene gameScene) {
        this.icon.paintIcon(gameScene, graphics, this.x, this.y);
    }

    public void move(int direction) {
        switch (direction){
            case Definition.MOVE_UP:
                this.hight ++;
                break;
            case Definition.MOVE_DOWN:
                this.hight --;
                break;
        }
    }
    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
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

    public void setHight(int hight) {
        this.hight = hight;
    }



    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void paint(Graphics graphics) {
    }

}
