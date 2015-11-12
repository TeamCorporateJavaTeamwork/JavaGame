package states;

import Game.GameEngine;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class VictoryState {

    public static boolean isOn;

    public void render(Graphics g, SpriteSheet victoryAnimation, SpriteSheet victoryAnimation2) {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    g.drawImage(victoryAnimation2.crop(GameEngine.tasks.victoryAnimationFireworks.getPosition(), GameEngine.tasks.victoryAnimationFireworks.getPosition2()), 210 + 183 * i, 100 + 120 * j, null);
                }
            }
        g.drawImage(victoryAnimation.crop(GameEngine.tasks.victoryAnimation.getPosition(), GameEngine.tasks.victoryAnimation.getPosition2()), 330, 110, null);
        g.drawImage(Assets.playAgain, 385, 335, 388, 89, null);
        g.drawImage(Assets.menu, 385, 435, 388, 89, null);
    }

    public static void resetValues () {
        GameEngine.tasks.victoryAnimation.resetAnimation();
        GameEngine.tasks.victoryAnimationFireworks.resetAnimation();
        VictoryState.isOn = false;
        GameEngine.resetPositions();
        GameEngine.puck.setVelocityX(0);
        GameEngine.puck.setVelocityY(0);
        GameEngine.player1.setScore(0);
        GameEngine.player2.setScore(0);
    }
}
