import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Animation implements KeyListener {

    private Player bird;

    public Animation (Player bird){
    this.bird = bird;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP:
                this.bird.move(Definition.MOVE_UP);
                break;
            case KeyEvent.VK_DOWN:
                this.bird.move(Definition.MOVE_DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
