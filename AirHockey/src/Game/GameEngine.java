package Game;

import Game.entities.Player;
import Game.entities.Puck;
import display.Display;
import Game.tasks.TaskManager;
import gfx.Assets;
import gfx.SpriteSheet;
import states.GameState;
import states.MenuState;
import states.StateManager;
import states.VictoryState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Runnable{
    private String title;
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private Thread thread;
    private boolean isRunning;
	private SpriteSheet numbers;
    private SpriteSheet alphabet;
	public static TaskManager tasks;

    private VictoryState victoryScreen;
	private MenuState mainMenu;
	private GameState game;

    public static Puck puck;
    public static Player player1;
    public static Player player2;
	public static StateManager State;
    private static boolean shouldCountDown;

	public GameEngine(String name) {
        this.title = name;
    }

    private void init() {
        Assets.init();
        this.display = new Display(this.title);

	    this.display.getCanvas().addKeyListener(new InputHandler());
	    this.display.getCanvas().addMouseListener(new MouseInputHandler());

	    State = new StateManager();
	    this.mainMenu = new MenuState();
        this.victoryScreen = new VictoryState();
	    this.game = new GameState();
        tasks = new TaskManager();

        this.numbers = new SpriteSheet(Assets.numbers, 60, 60);
        this.alphabet = new SpriteSheet(Assets.alphabet, 30, 30);

        player1 = new Player("Player 1", 250, 325, 1);
        player2 = new Player("Player 2", 800, 325, 2);
        puck = new Puck();
    }

    private void tick() {
        if(!GameEngine.isShouldCountDown()&&State.getState() == StateManager.STATES.GAME) {
            player1.getMallet().tick();
		    player2.getMallet().tick();
            puck.tick();
        }

    }
    public static void resetPositions(){
        puck.reset();
        player1.getMallet().reset(1);
        player2.getMallet().reset(2);
    }

    private void render() {
	    this.bs = this.display.getCanvas().getBufferStrategy();

	    if(this.bs == null) {
		    this.display.getCanvas().createBufferStrategy(2);
		    return;
	    }
	    this.g = this.bs.getDrawGraphics();

        this.g.clearRect(0, 0, Display.WIDTH, Display.HEIGHT);

        //Start Drawing
	    this.g.drawImage(Assets.blackBG, 0,0, 1200, 800, null);

	    if(State.getState() == StateManager.STATES.GAME) {
		    this.game.render(this.g, player1, player2, puck, this.numbers, this.alphabet);
	    } else if(State.getState() == StateManager.STATES.MENU) {
		    this.mainMenu.render(this.g);
	    } else if(State.getState() == StateManager.STATES.VICTORY) {
            this.victoryScreen.render(this.g, alphabet);
        }

        //Stop Drawing
        this.g.dispose();
        this.bs.show();
    }

	@Override
    public void run() {
        this.init();

        int fps = 60;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long timeNow;
        long lastTime = System.nanoTime();

        while(isRunning) {
            timeNow = System.nanoTime();
            delta += (timeNow - lastTime) / timePerTick;
            tasks.tick(timeNow - lastTime);
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

    public static String getPlayerName(int player) {
        String playerName;
        do {
            playerName = JOptionPane.showInputDialog(null, "Please enter player " + player + " name:", "Please enter player name", JOptionPane.INFORMATION_MESSAGE, null, null, "Player " + player).toString(); //JOptionPane.showInputDialog("Please enter player " + player + " name:", "Player " + player);
        } while (playerName == null || playerName.equals("") || playerName.equals(" "));

        return playerName;
    }

    public static boolean isShouldCountDown() {
        return shouldCountDown;
    }

    public static void setShouldCountDown(boolean should) {
        shouldCountDown = should;
    }
}
