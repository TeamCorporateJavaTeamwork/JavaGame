package Game.tasks;

import Game.GameEngine;
import states.GameState;

public class TaskManager {

    private Task puckAnimation;
    public Task countDown;
    public Task victoryAnimation;
    public Task victoryAnimationFireworks;

    public TaskManager() {
        //every digit takes 1/2 of a second
        countDown = new Task(2, 3);
        puckAnimation = new Task(14, 0);
        victoryAnimation = new Task(25, 0);
        victoryAnimationFireworks = new Task(18, 0);
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
        if(victoryAnimation.isOn) {
            victoryAnimation.delta += timeDeduct / victoryAnimation.timePerTick;
            if(victoryAnimation.delta >= 1) {
                victoryAnimationPos();
                victoryAnimation.delta--;
            }
        }
        if(victoryAnimationFireworks.isOn) {
            victoryAnimationFireworks.delta += timeDeduct / victoryAnimationFireworks.timePerTick;
            if(victoryAnimationFireworks.delta >= 1) {
                victoryAnimationFireworksPos();
                victoryAnimationFireworks.delta--;
            }
        }
    }

    private void victoryAnimationPos() {
        victoryAnimation.position++;
        if(victoryAnimation.position > 4 && victoryAnimation.position2 == 0) {
            victoryAnimation.position = 0;
            victoryAnimation.position2++;
        }
        if(victoryAnimation.position2 == 1 && victoryAnimation.position == 5) {
            victoryAnimation.position = 4;
            victoryAnimation.isOn = false;
            victoryAnimationFireworks.isOn = true;
        }
    }
    private void victoryAnimationFireworksPos() {
        victoryAnimationFireworks.position++;
        if(victoryAnimationFireworks.position > 7 && victoryAnimationFireworks.position2 < 2)
        {
            victoryAnimationFireworks.position = 0;
            victoryAnimationFireworks.position2++;
        }
        if(victoryAnimationFireworks.position2 == 2 && victoryAnimation.position == 8) {
            victoryAnimation.position = 7;
            victoryAnimationFireworks.isOn = false;
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