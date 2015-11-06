package Game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Game.player1.isMovingUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.player1.isMovingDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.player1.isMovingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.player1.isMovingRight = true;
        }
        if (keyCode == KeyEvent.VK_W) {
            Game.player2.isMovingUp = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            Game.player2.isMovingLeft = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            Game.player2.isMovingDown = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            Game.player2.isMovingRight = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Game.player1.isMovingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.player1.isMovingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.player1.isMovingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.player1.isMovingRight = false;
        }
        if (keyCode == KeyEvent.VK_W) {
            Game.player2.isMovingUp = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            Game.player2.isMovingLeft = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            Game.player2.isMovingDown = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            Game.player2.isMovingRight = false;
        }
    }
}
