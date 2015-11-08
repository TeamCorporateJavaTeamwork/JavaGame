package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            GameEngine.player2.getMallet().isMovingUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            GameEngine.player2.getMallet().isMovingDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            GameEngine.player2.getMallet().isMovingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            GameEngine.player2.getMallet().isMovingRight = true;
        }
        if (keyCode == KeyEvent.VK_W) {
            GameEngine.player1.getMallet().isMovingUp = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            GameEngine.player1.getMallet().isMovingLeft = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            GameEngine.player1.getMallet().isMovingDown = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            GameEngine.player1.getMallet().isMovingRight = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            GameEngine.player2.getMallet().isMovingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            GameEngine.player2.getMallet().isMovingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            GameEngine.player2.getMallet().isMovingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            GameEngine.player2.getMallet().isMovingRight = false;
        }
        if (keyCode == KeyEvent.VK_W) {
            GameEngine.player1.getMallet().isMovingUp = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            GameEngine.player1.getMallet().isMovingLeft = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            GameEngine.player1.getMallet().isMovingDown = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            GameEngine.player1.getMallet().isMovingRight = false;
        }
    }
}
