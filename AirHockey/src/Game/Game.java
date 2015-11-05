package Game;

import Game.entities.BoundingBox;
import Game.entities.Mallet;
import Game.entities.Puck;
import display.Display;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    private String title;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private InputHandler inputHandler;

    private Thread thread;
    private boolean isRunning;

    public static Puck puck;
    public static Mallet player1;
    public static Mallet player2;

    public Game(String name) {
        this.title = name;
    }

    private void init() {
        Assets.init();
        this.display = new Display(this.title);
        this.inputHandler = new InputHandler(this.display);

        this.puck = new Puck();
        this.player1 = new Mallet("Gosho", 250, 325,1);
        this.player2 = new Mallet("Pesho", 800, 325,2);
    }

    private void tick() {
        player1.tick();
        player2.tick();
        puck.tick();
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

        player1.renderBlue(g);
        player2.renderRed(g);
        puck.render(g);

        //Stop Drawing

        this.g.dispose();
        this.bs.show();
    }

    @Override
    public void run() {
        this.init();

        int fps = 30;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;

        long timeNow;
        long lastTime = System.nanoTime();

        while(isRunning) {
            timeNow = System.nanoTime();

            delta += (timeNow - lastTime) / timePerTick;

            lastTime = timeNow;

            if(delta >= 1) {
                this.tick();
                this.render();
                delta--;
            }
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
