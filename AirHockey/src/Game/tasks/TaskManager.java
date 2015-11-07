package Game.tasks;

import Game.Game;

public class TaskManager {

    public static Task puckAnimation;

    public TaskManager() {
        this.puckAnimation = new Task(14);
    }

    public static void tick(long timeDeduct) {
        if(puckAnimation.isOn) {
            puckAnimation.delta += timeDeduct / puckAnimation.timePerTick;
            if(puckAnimation.delta >= 1) {
                puckAnimationPos();
                puckAnimation.delta--;
            }
        }
    }

    private static void puckAnimationPos() {
        puckAnimation.position++;
        if(puckAnimation.position > 9) {
            puckAnimation.position = 0;
        }
    }

}
