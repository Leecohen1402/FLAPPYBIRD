import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameScene extends JPanel {
    private int sceneId;
    private Player bird;
    private ImageIcon player;
    private Obstacles obstacles;
    private ObstacleOne obstacleOne;
    private ObstacleTwo obstaclesTwo;
    private ObstacleThree obstacleThree;
    private JLabel welcome = new JLabel();
    private JLabel gameRules1 = new JLabel();
    private JLabel gameRules2 = new JLabel();
    private JLabel gameRules3 = new JLabel();

    public GameScene() {
        this.setLayout(null);
        JButton iAgree = new JButton("Let's start!");
        iAgree.setBounds(Definition.BUTTON_X, Definition.BUTTON_Y, Definition.BUTTON_WIDTH, Definition.BUTTON_HIGHT);
        iAgree.addActionListener((event -> {
            this.sceneId = Definition.GAME_SCENE;
        }));
        this.add(iAgree);
        iAgree.setBackground(Color.CYAN);
        this.bird = new Player(Definition.BIRD_PLAYER_X, Definition.BIRD_PLAYER_Y);
        rules();
        this.player = new ImageIcon("images/flappy birds.jpg");
        Image image = player.getImage(); // transform it
        Image newImg = image.getScaledInstance(Definition.PLAYER_X4, Definition.PLAYER_Y4, Image.SCALE_SMOOTH); // scale it the smooth way
        player = new ImageIcon(newImg);  // transform it back
        this.obstacles = new Obstacles(Definition.OBSTACLES_X, Definition.OBSTACLES_Y, Definition.OBSTACLES_WIDTH, Definition.OBSTACLES_HIGHT);
        this.obstacleOne = new ObstacleOne(Definition.OBSTACLE_ONE_X, Definition.OBSTACLE_ONE_Y, Definition.OBSTACLE_ONE_WIDTH, Definition.OBSTACLE_ONE_HIGHT);
        this.obstaclesTwo = new ObstacleTwo(Definition.OBSTACLE_TWO_X, Definition.OBSTACLE_TWO_Y, Definition.OBSTACLE_TWO_WIDTH, Definition.OBSTACLE_TWO_HIGHT);
        this.obstacleThree = new ObstacleThree(Definition.OBSTACLE_THREE_X, Definition.OBSTACLE_THREE_Y, Definition.OBSTACLE_THREE_WIDTH, Definition.OBSTACLE_THREE_HIGHT);
        Animation animation = new Animation(this.bird);
        this.addKeyListener(animation);
        mainGameLoop();

    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                if (collision1(bird, obstacles)){
                this.bird.setAlive(false);
                }
                if (collision2(bird,obstacleOne)){
                    this.bird.setAlive(false);
                }
                if (collision3(bird,obstaclesTwo)){
                    this.bird.setAlive(false);
                }
                if (collision4(bird, obstacleThree)){
                    this.bird.setAlive(false);
                }
                repaint();
                try {

                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void paint(Graphics graphics) {
        super.paint(graphics);
        switch (this.sceneId) {
            case Definition.MENU:
                this.welcome.setVisible(true);
                this.gameRules1.setVisible(true);
                this.gameRules2.setVisible(true);
                this.gameRules3.setVisible(true);
                break;
            case Definition.GAME_SCENE:
                this.welcome.setVisible(false);
                this.gameRules1.setVisible(false);
                this.gameRules2.setVisible(false);
                this.gameRules3.setVisible(false);
                this.bird.paint(graphics, this);
                this.setBackground(Color.WHITE);
                if (this.bird.isAlive()) {
                    this.player.paintIcon(this, graphics, Definition.PAINT_ICONE_X, Definition.PAINT_ICONE_Y);
                }
                this.obstacles.paint(graphics);
                this.obstacleOne.paint(graphics);
                this.obstaclesTwo.paint(graphics);
                this.obstacleThree.paint(graphics);
                break;
        }
    }

    private void rules() {
        welcome.setText("Welcome to Crazy bird ! ! rules of the game:");
        welcome.setBounds(Definition.TEXT_X, Definition.TEXT_Y, Definition.TEXT_WIDTH, Definition.TEXT_HIGHT);
        this.add(welcome);
        gameRules1.setText("1. use the up and down arrows on the keyboard keys");
        gameRules1.setBounds(Definition.TEXT_X1, Definition.TEXT_Y1, Definition.TEXT_WIDTH1, Definition.TEXT_HIGHT1);
        this.add(gameRules1);
        gameRules2.setText("2. don't touch the obstacles or you lose!!");
        gameRules2.setBounds(Definition.TEXT_X2, Definition.TEXT_Y2, Definition.TEXT_WIDTH2, Definition.TEXT_HIGHT2);
        this.add(gameRules2);

    }

    private boolean collision1 (Player bird, Obstacles obstacles){
        Rectangle birdRectangle = new Rectangle(bird.getX(),bird.getY(),bird.getWidth(),bird.getHight());
        Rectangle obstaclesRectangle = new Rectangle(obstacles.getX(),obstacles.getY(),obstacles.getWidth(),obstacles.getHight());
        boolean collision1 = birdRectangle.intersects(obstaclesRectangle);
        return collision1;
    }

    private boolean collision2 (Player bird, ObstacleOne obstacleOne){
        Rectangle birdRectangle = new Rectangle(bird.getX(),bird.getY(),bird.getWidth(),bird.getHight());
        Rectangle obstacleOneRectangle = new Rectangle(obstacleOne.getX(),obstacleOne.getY(),obstacleOne.getWidth(),obstacleOne.getHight());
        boolean collision2 = birdRectangle.intersects(obstacleOneRectangle);
        return collision2;
    }

    private boolean collision3 (Player bird, ObstacleTwo obstaclesTwo){
        Rectangle birdRectangle = new Rectangle(bird.getX(),bird.getY(),bird.getWidth(),bird.getHight());
        Rectangle obstacleTwoRectangle = new Rectangle(obstaclesTwo.getX(),obstaclesTwo.getY(),obstaclesTwo.getWidth(),obstaclesTwo.getHight());
        boolean collision3 = birdRectangle.intersects(obstacleTwoRectangle);
        return collision3;
    }

    private boolean collision4 (Player bird, ObstacleThree obstacleThree){
        Rectangle birdRectangle = new Rectangle(bird.getX(),bird.getY(),bird.getWidth(),bird.getHight());
        Rectangle obstacleThreeRectangle = new Rectangle(obstacleThree.getX(),obstacleThree.getY(),obstacleThree.getWidth(),obstacleThree.getHight());
        boolean collision4 = birdRectangle.intersects(obstacleThreeRectangle);
        return collision4;
    }
}
