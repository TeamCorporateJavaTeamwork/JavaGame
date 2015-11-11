package states;

import Game.GameEngine;
import gfx.SpriteSheet;

import java.awt.*;

public class VictoryState {

    public void render(Graphics g, SpriteSheet alphabet, SpriteSheet victoryAnimation, SpriteSheet victoryAnimation2) {
        if(GameEngine.tasks.victoryAnimationFireworks.isOn) {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    g.drawImage(victoryAnimation2.crop(GameEngine.tasks.victoryAnimationFireworks.position, GameEngine.tasks.victoryAnimationFireworks.position2), 210 + 183 * i, 100 + 120 * j, null);
                }
            }
        }
        g.drawImage(victoryAnimation.crop(GameEngine.tasks.victoryAnimation.position, GameEngine.tasks.victoryAnimation.position2), 340, 110, null);
    }
}
