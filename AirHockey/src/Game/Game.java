package Game;

import display.Display;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    private String title;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    private Thread thread;
    private boolean isRunning;

    public Game(String name) {
        this.title = name;
    }

    private void init() {
        Assets.init();
        this.display = new Display(this.title);
    }

    private void tick() {

    }

    private void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if(this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = this.bs.getDrawGraphics();

        g.clearRect(0, 0, this.display.WIDTH, this.display.HEIGHT);

        //Start Drawing

        g.drawImage(Assets.background, 180, 80, 800, 600, null);

        //Stop Drawing

        this.g.dispose();
        this.bs.show();
    }

    @Override
    public void run() {
        this.init();

        while(isRunning) {
            this.tick();
            this.render();
        }

        this.stop();
    }

    public synchronized void start() {
        this.thread = new Thread(this);

        this.isRunning = true;
        this.thread.start();
    }

    public synchronized void stop() {
        try {
            this.isRunning = false;
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
