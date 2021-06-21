import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public static void main(String[] args) {
         new Game();
    }

    public Game(){
        this.init();

    }

    private void init(){
        this.setVisible(true);
        this.setSize(Definition.WINDOW_WIDTH, Definition.WINDOW_HIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Crazy bird");
        GameScene gameScene = new GameScene();
        gameScene.setBounds(Definition.GAME_SCENE_BOUNDS, Definition.GAME_SCENE_BOUNDS, Definition.WINDOW_WIDTH, Definition.WINDOW_HIGHT);
        this.add(gameScene);

    }

}
