package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage puck;
    public static BufferedImage puckAnim;
    public static BufferedImage numbers;
    public static BufferedImage blackBG;
    public static BufferedImage malletTemplate;

    public static void init() {
        background = ImageLoader.loadImage("/Textures/hockey field.png");
        puck = ImageLoader.loadImage("/Textures/puck.png");
        puckAnim = ImageLoader.loadImage("/Textures/puckAnimation.png");
        numbers = ImageLoader.loadImage("/Textures/numbers.png");
        blackBG = ImageLoader.loadImage("/Textures/background.jpg");
        malletTemplate = ImageLoader.loadImage("/Textures/malletTemplate.png");
    }
}
