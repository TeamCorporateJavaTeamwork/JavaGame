package Game.tasks;

public class Task {
    public int fps;
    public double delta = 0;
    public double timePerTick;
    public boolean isOn = true;
    //if task is to moving anim
    public int position = 0;

    public Task(int fps) {
        this.fps = fps;
        this.timePerTick = 1_000_000_000.0 / this.fps;
    }

    public void start() {
        this.delta = 0;
        this.isOn = true;
    }

    public void stop() {
        this.isOn = false;
        this.position = 0;
    }
}
