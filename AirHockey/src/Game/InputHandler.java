package Game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by krasimir on 11/4/2015.
 */
public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

    }
}
