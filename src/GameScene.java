import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScene extends JPanel implements ActionListener {
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
    private JButton iAgree, tryAgain;
    Timer timer;

    public GameScene() {
        timer = new Timer(Definition.DELAY, this);
        this.setLayout(null);
        iAgree = new JButton("Let's start!");
        tryAgain = new JButton("Double click to try again! :)");
        tryAgain.setBounds(Definition.BUTTON_X, Definition.BUTTON_Y, Definition.BUTTON_WIDTH, Definition.BUTTON_HIGHT);
        iAgree.setBounds(Definition.BUTTON_X, Definition.BUTTON_Y, Definition.BUTTON_WIDTH, Definition.BUTTON_HIGHT);
        iAgree.addActionListener((event -> {
            this.sceneId = Definition.GAME_SCENE;
        }));
        tryAgain.addActionListener(e -> {
            sceneId = Definition.GAME_SCENE;
            bird.setAlive(true);
            remove(tryAgain);
        });
        this.add(iAgree);
        iAgree.setBackground(Color.CYAN);
        this.bird = new Player(Definition.BIRD_PLAYER_X, Definition.BIRD_PLAYER_Y, this);
        rules();
        this.player = new ImageIcon("images/flappy birds.jpg");
        Image image = player.getImage(); // transform it
        Image newImg = image.getScaledInstance(Definition.PLAYER_X4, Definition.PLAYER_Y4, Image.SCALE_SMOOTH); // scale it the smooth way
        player = new ImageIcon(newImg);  // transform it back
        this.obstacles = new Obstacles(Definition.OBSTACLES_X, Definition.OBSTACLES_Y, Definition.OBSTACLES_WIDTH, Definition.OBSTACLES_HIGHT);
        this.obstacleOne = new ObstacleOne(Definition.OBSTACLE_ONE_X, Definition.OBSTACLE_ONE_Y, Definition.OBSTACLE_ONE_WIDTH, Definition.OBSTACLE_ONE_HIGHT);
        this.obstaclesTwo = new ObstacleTwo(Definition.OBSTACLE_TWO_X, Definition.OBSTACLE_TWO_Y, Definition.OBSTACLE_TWO_WIDTH, Definition.OBSTACLE_TWO_HIGHT);
        this.obstacleThree = new ObstacleThree(Definition.OBSTACLE_THREE_X, Definition.OBSTACLE_THREE_Y, Definition.OBSTACLE_THREE_WIDTH, Definition.OBSTACLE_THREE_HIGHT);

        addKeys();

        mainGameLoop();

    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                if (collision(bird, obstacles)) {
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
        chuckCollision();
        super.paint(graphics);
        switch (this.sceneId) {
            case Definition.MENU:
                this.welcome.setVisible(true);
                this.gameRules1.setVisible(true);
                this.gameRules2.setVisible(true);
                this.gameRules3.setVisible(true);
                break;
            case Definition.GAME_SCENE:
                timer.start();
                requestFocusInWindow();
                iAgree.setVisible(false);
                this.welcome.setVisible(false);
                this.gameRules1.setVisible(false);
                this.gameRules2.setVisible(false);
                this.gameRules3.setVisible(false);
                this.bird.paint(graphics, this);
                this.setBackground(Color.WHITE);
                if (this.bird.isAlive()) {
                    this.player.paintIcon(this, graphics, bird.getX(), bird.getY());
                } else {
                    sceneId = Definition.LOSE;
                }
                this.obstacles.paint(graphics);
                this.obstacleOne.paint(graphics);
                this.obstaclesTwo.paint(graphics);
                this.obstacleThree.paint(graphics);
                break;
            case Definition.WIN:
                graphics.setFont(new Font("Arial", Font.BOLD, Definition.FONT_SIZE));
                graphics.drawString("you win!", Definition.YOU_WIN_X, Definition.YOU_WIN_Y);
                break;
            case Definition.LOSE:
                timer.stop();
                setBackground(new Color(Definition.R, Definition.G, Definition.B));
                graphics.setFont(new Font("Arial", Font.BOLD, Definition.FONT_SIZE));
                graphics.drawString("you Lose!!!", Definition.WINDOW_WIDTH / 2 - 140, Definition.WINDOW_HIGHT / 2 - 118);
                add(tryAgain);
                bird.setX(Definition.BIRD_PLAYER_X);
                bird.setY(Definition.BIRD_PLAYER_Y);

        }

    }

    private void chuckCollision() {
        boolean one, two, three, four;
        Rectangle birdBox = bird.getBox();
        one = birdBox.intersects(obstacles.getBox());
        two = birdBox.intersects(obstacleOne.getBox());
        three = birdBox.intersects(obstaclesTwo.getBox());
        four = birdBox.intersects(obstacleThree.getBox());
        if (one || two || three || four) {
            bird.setAlive(false);
        }
    }

    private void addKeys() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        bird.move(Definition.MOVE_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        bird.move(Definition.MOVE_DOWN);
                        break;
                    case KeyEvent.VK_RIGHT:
                        bird.move(Definition.MOVE_RIGHT);
                        break;
                    case KeyEvent.VK_LEFT:
                        bird.move(Definition.MOVE_LEFT);
                        break;
                }
                repaint();
            }
        });
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

    private boolean collision(Player bird, Obstacles obstacles) {
        Rectangle birdRectangle = new Rectangle(bird.getX(), bird.getY(), bird.getWidth(), bird.getHight());
        Rectangle obstaclesRectangle = new Rectangle(obstacles.getX(), obstacles.getY(), obstacles.getWidth(), obstacles.getHight());
        boolean collision = birdRectangle.intersects(obstaclesRectangle);
        return collision;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move(Definition.MOVE_RIGHT);
    }
}