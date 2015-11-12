package Game.tasks;

import Game.GameEngine;

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
        if(puckAnimation.isOn()) {
            puckAnimation.setDelta(puckAnimation.getDelta() + timeDeduct / puckAnimation.getTimePerTick());
            if(puckAnimation.getDelta() >= 1) {
                puckAnimationPos();
                puckAnimation.setDelta(puckAnimation.getDelta() - 1);
            }
        }
        if(GameEngine.isCountDownNeeded()) {
            countDown.setDelta(countDown.getDelta() + timeDeduct / countDown.getTimePerTick());
            if(countDown.getDelta() >= 1) {
                countDownAnimationPos();
                countDown.setDelta(countDown.getDelta() - 1);
            }
        }
        if(victoryAnimation.isOn()) {
            victoryAnimation.setDelta(victoryAnimation.getDelta() + timeDeduct / victoryAnimation.getTimePerTick());
            if(victoryAnimation.getDelta() >= 1) {
                victoryAnimationPos();
                victoryAnimation.setDelta(victoryAnimation.getDelta() - 1);
            }
        }
        if(victoryAnimationFireworks.isOn()) {
            victoryAnimationFireworks.setDelta(victoryAnimationFireworks.getDelta() + timeDeduct / victoryAnimationFireworks.getTimePerTick());
            if(victoryAnimationFireworks.getDelta() >= 1) {
                victoryAnimationFireworksPos();
                victoryAnimationFireworks.setDelta(victoryAnimationFireworks.getDelta() - 1);
            }
        }
    }

    private void victoryAnimationPos() {
        victoryAnimation.setPosition(victoryAnimation.getPosition() + 1);
        if(victoryAnimation.getPosition() > 4 && victoryAnimation.getPosition2() == 0) {
            victoryAnimation.setPosition(0);
            victoryAnimation.setPosition2(victoryAnimation.getPosition2() + 1);
        }
        if(victoryAnimation.getPosition2() == 1 && victoryAnimation.getPosition() == 5) {
            victoryAnimation.setPosition(4);
            victoryAnimation.setOn(false);
            victoryAnimationFireworks.setOn(true);
        }
    }
    private void victoryAnimationFireworksPos() {
        victoryAnimationFireworks.setPosition(victoryAnimationFireworks.getPosition() + 1);
        if(victoryAnimationFireworks.getPosition() > 7)
        {
            victoryAnimationFireworks.setPosition(0);
            victoryAnimationFireworks.setPosition2(victoryAnimation.getPosition2() + 1);
            if(victoryAnimationFireworks.getPosition2() > 2) {
                victoryAnimationFireworks.setPosition2(0);
            }
        }
    }


    private void puckAnimationPos() {
        puckAnimation.setPosition(puckAnimation.getPosition() + 1);
        if(puckAnimation.getPosition() > 9) {
            puckAnimation.setPosition(0);
        }
    }

    private void countDownAnimationPos(){
        countDown.setPosition(countDown.getPosition() - 1);
        if(countDown.getPosition() < 1){
            countDown.setPosition(3);
            //if 0 is reached > stop counting down;
            GameEngine.setIsCountDownNeeded(false);
        }
    }

//    public Task getPuckAnimation() {
//
//        return puckAnimation;
//    }

}