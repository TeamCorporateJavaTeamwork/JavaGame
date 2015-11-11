package states;

import Game.GameEngine;
import gfx.Assets;

import java.awt.*;

public class PauseState {
    public void render(Graphics g) {
        g.drawImage(Assets.paused, 440, 264, 280, 235, null);
    }
}
