package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage redPlayer;
    public static BufferedImage bluePlayer;
    public static BufferedImage puck;

    public static void init() {
        background = ImageLoader.loadImage("/Textures/hockey field.png");
        redPlayer = ImageLoader.loadImage("/Textures/red-mallet.png");
        bluePlayer = ImageLoader.loadImage("/Textures/blue-mallet.png");
        puck = ImageLoader.loadImage("/Textures/puck.png");
    }
}
