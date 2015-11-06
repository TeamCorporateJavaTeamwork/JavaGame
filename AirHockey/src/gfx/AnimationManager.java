package gfx;

public class AnimationManager {

    private static int puckAnimationFps = 10;
    private static double puckAnimDelta = 0;
    private static double puckAnimTimePerTick = 1_000_000_000.0 / puckAnimationFps;
    public static int puckAnimationPos = 0;
    public static boolean puckAnimationCondition = true;

        public AnimationManager() {
    }

    public static void tick(long timeNow, long lastTime) {
        if(puckAnimationCondition) {
            puckAnimDelta += (timeNow - lastTime) / puckAnimTimePerTick;
            puckAnimation();
        }
    }

    private static void puckAnimation() {
        if(puckAnimDelta >= 1) {
            puckAnimationPos++;
            if(puckAnimationPos > 9) {
                puckAnimationPos = 0;
            }
            puckAnimDelta--;
        }
    }

    public static void puckAnimationStop() {
        puckAnimationCondition = false;
        puckAnimationPos = 0;
    }

    public static void puckAnimationStart() {
        puckAnimationCondition = true;
    }

}
