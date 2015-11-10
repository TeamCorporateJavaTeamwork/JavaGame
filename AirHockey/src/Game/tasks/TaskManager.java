package Game.tasks;

import Game.GameEngine;
import states.GameState;

public class TaskManager {

    private Task puckAnimation;
    public Task countDown;

    public TaskManager() {
        countDown = new Task(3, 3);
        puckAnimation = new Task(14, 0);
    }

    public void tick(long timeDeduct) {
        if(puckAnimation.isOn) {
            puckAnimation.delta += timeDeduct / puckAnimation.timePerTick;
            if(puckAnimation.delta >= 1) {
                puckAnimationPos();
                puckAnimation.delta--;
            }
        }
        if(GameEngine.isShouldCountDown()) {
            countDown.delta += timeDeduct / countDown.timePerTick;
            if(countDown.delta >= 1) {
                countDownAnimationPos();
                countDown.delta--;
            }
        }
    }

    private void puckAnimationPos() {
        puckAnimation.position++;
        if(puckAnimation.position > 9) {
            puckAnimation.position = 0;
        }
    }

    private void countDownAnimationPos(){
        countDown.position--;
        if(countDown.position<1){
            countDown.position=3;
            //if 0 is reached > stop counting down;
            GameEngine.setShouldCountDown(false);
        }
    }

    public Task getPuckAnimation() {

        return puckAnimation;
    }

}