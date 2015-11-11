package states;

import Game.GameEngine;
import gfx.Assets;

import java.awt.*;

public class PauseState {
    public void render(Graphics g) {
        g.drawImage(Assets.paused, 490, 160, 181, 103, null);
    }
}
