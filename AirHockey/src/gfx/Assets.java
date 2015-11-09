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
    public static BufferedImage settings;
    public static BufferedImage quitGame;

    public static void init() {
        background = ImageLoader.loadImage("/Textures/hockey field.png");
        puck = ImageLoader.loadImage("/Textures/puck.png");
        puckAnim = ImageLoader.loadImage("/Textures/puckAnimation.png");
        numbers = ImageLoader.loadImage("/Textures/numbers.png");
        blackBG = ImageLoader.loadImage("/Textures/backgrounds/main.jpg");
        malletTemplate = ImageLoader.loadImage("/Textures/malletTemplate.png");
        alphabet = ImageLoader.loadImage("/Textures/alphabet.png");
        headerBackground = ImageLoader.loadImage("/Textures/backgrounds/header.png");
        header = ImageLoader.loadImage("/Textures/header.png");
        playGame = ImageLoader.loadImage("/Textures/buttons/playGame.png");
        settings = ImageLoader.loadImage("/Textures/buttons/settings.png");
        quitGame = ImageLoader.loadImage("/Textures/buttons/quitGame.png");
    }
}
