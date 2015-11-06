package gfx;

import Game.Game;
import Game.entities.Mallet;

public class AnimationManager {

    private static int puckAnimationFps = 20;
    private static double puckAnimDelta = 0;
    private static double puckAnimTimePerTick = 1_000_000_000.0 / puckAnimationFps;
    public static int puckAnimationPos = 0;
    public static boolean puckAnimationCondition = true;

    private static int malletAnimationFps = 150;
    private static double malletAnimDelta = 0;
    private static double malletAnimTimePerTick = 1_000_000_000.0 / malletAnimationFps;
    public static int malletAnimationPos = 0;
    public static boolean malletAnimationCondition = true;

        public AnimationManager() {
    }

    public static void tick(long timeNow, long lastTime, Mallet player1, Mallet player2) {
        if(puckAnimationCondition) {
            puckAnimDelta += (timeNow - lastTime) / puckAnimTimePerTick;
            if(puckAnimDelta >= 1) {
                puckAnimation();
                puckAnimDelta--;
            }
        }
        if(malletAnimationCondition) {
            malletAnimDelta += (timeNow - lastTime) / malletAnimTimePerTick;
            if(malletAnimDelta >= 1) {
                malletAnimation(player1, player2);
                malletAnimDelta--;
            }
        }
    }

    private static void puckAnimation() {
        puckAnimationPos++;
        if(puckAnimationPos > 9) {
            puckAnimationPos = 0;
        }
        puckAnimDelta--;
    }

    public static void puckAnimationStop() {
        puckAnimationCondition = false;
        puckAnimationPos = 0;
    }

    public static void puckAnimationStart() {
        puckAnimDelta = 0;
        puckAnimationCondition = true;
    }

    private static void malletAnimation(Mallet player1, Mallet player2) {
        player1.velocitySum();
        player2.velocitySum();
    }

    public static void malletAnimationStart() {
        puckAnimationCondition = false;
        puckAnimationPos = 0;
    }

    public static void malletAnimationStop() {
        puckAnimDelta = 0;
        puckAnimationCondition = true;
    }

}
