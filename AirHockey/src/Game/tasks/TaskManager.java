package Game.tasks;

public class TaskManager {

	private Task puckAnimation;

    public TaskManager() {
        puckAnimation = new Task(14);
    }

    public void tick(long timeDeduct) {
        if(puckAnimation.isOn) {
            puckAnimation.delta += timeDeduct / puckAnimation.timePerTick;
            if(puckAnimation.delta >= 1) {
                puckAnimationPos();
                puckAnimation.delta--;
            }
        }
    }

    private void puckAnimationPos() {
        puckAnimation.position++;
        if(puckAnimation.position > 9) {
            puckAnimation.position = 0;
        }
    }

	public Task getPuckAnimation() {

		return puckAnimation;
	}

}
