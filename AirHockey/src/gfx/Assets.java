package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage puck;
    public static BufferedImage puckAnim;
    public static BufferedImage numbers;
    public static BufferedImage blackBG;
    public static BufferedImage malletTemplate;
    public static BufferedImage alphabet;
    public static BufferedImage headerBackground;
    public static BufferedImage header;
    public static BufferedImage playGame;
    public static BufferedImage settingsButton;
    public static BufferedImage quitGame;
    public static BufferedImage paused;
    public static BufferedImage gates;
    public static BufferedImage victoryAnim;
    public static BufferedImage victoryAnim2;
    public static BufferedImage info;
    public static BufferedImage playAgain;
    public static BufferedImage menu;
    public static BufferedImage settings;
    public static BufferedImage back;

    public static void init() {
        background = ImageLoader.loadImage("/Textures/field/hockey field.png");
        puck = ImageLoader.loadImage("/Textures/puck.png");
        puckAnim = ImageLoader.loadImage("/Textures/animations/puckAnimation.png");
        numbers = ImageLoader.loadImage("/Textures/spriteSheets/numbers.png");
        blackBG = ImageLoader.loadImage("/Textures/backgrounds/main.jpg");
        malletTemplate = ImageLoader.loadImage("/Textures/malletTemplate.png");
        alphabet = ImageLoader.loadImage("/Textures/spriteSheets/alphabet.png");
        headerBackground = ImageLoader.loadImage("/Textures/backgrounds/header.png");
        header = ImageLoader.loadImage("/Textures/header.png");
        playGame = ImageLoader.loadImage("/Textures/buttons/playGame.png");
        settingsButton = ImageLoader.loadImage("/Textures/buttons/settings.png");
        quitGame = ImageLoader.loadImage("/Textures/buttons/quitGame.png");
        paused = ImageLoader.loadImage("/Textures/pause.png");
        gates = ImageLoader.loadImage("/Textures/field/gates.png");
        victoryAnim = ImageLoader.loadImage("/Textures/animations/victoryAnimation.png");
        victoryAnim2 = ImageLoader.loadImage("/Textures/animations/victoryAnimation2.png");
        info = ImageLoader.loadImage("/Textures/info.png");
        playAgain = ImageLoader.loadImage("/Textures/buttons/playAgain.png");
        menu = ImageLoader.loadImage("/Textures/buttons/menu.png");
        settings = ImageLoader.loadImage("/Textures/settings.png");
        back = ImageLoader.loadImage("/Textures/buttons/back.png");
    }
}
