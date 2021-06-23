import javax.swing.*;
import java.awt.*;

public class Player {
    private ImageIcon icon;
    private int x;
    private int y;
    private int width;
    private int hight;
    private boolean alive;
    private Rectangle box;

    public Player(int x, int y, GameScene gameScene) {
        this.icon = new ImageIcon("image/bird.png");
        this.x = x;
        this.y = y;
        this.alive = true;
        box = new Rectangle();
    }

    public void paint(Graphics graphics, GameScene gameScene) {
        box.setBounds(x,y,Definition.PLAYER_WIDTH-45,Definition.PLAYER_HIGH-45);
        if (x >= Definition.WINDOW_WIDTH-Definition.PLAYER_WIDTH)
            gameScene.setSceneId(Definition.WIN);
        this.icon.paintIcon(gameScene, graphics, this.x, this.y);
    }

    public void move(int direction) {
        switch (direction) {
            case Definition.MOVE_UP:
                if (y > 0)
                    this.y -= 10;
                break;
            case Definition.MOVE_DOWN:
                if (y <= Definition.WINDOW_HIGHT-Definition.PLAYER_HIGH)
                    this.y += 10;
                break;
            case Definition.MOVE_RIGHT:
                if (x <= Definition.WINDOW_WIDTH-Definition.PLAYER_WIDTH)
                    this.x += 10;
                break;
            case Definition.MOVE_LEFT:
                if (x > 0)
                    this.x -= 10;
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

    public Rectangle getBox() {
        return box;
    }
}
