package Game.tasks;

public class Task {
    private int fps;
    private double delta = 0;
    private double timePerTick;
    private boolean isOn;

    //if task is to moving anim
    private int position;
    private int position2;

    public Task(int fps, int pos) {
        this.fps = fps;
        this.timePerTick = 1_000_000_000.0 / this.fps;
        this.position = pos;
        this.position2 = 0;
    }

//    public void start() {
//        this.delta = 0;
//        this.isOn = true;
//    }
//
//    public void stop() {
//        this.isOn = false;
//        this.position = 0;
//    }

    public void resetAnimation() {
        this.isOn = false;
        this.position = 0;
        this.position2 = 0;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getTimePerTick() {
        return timePerTick;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition2() {
        return position2;
    }

    public void setPosition2(int position2) {
        this.position2 = position2;
    }
}