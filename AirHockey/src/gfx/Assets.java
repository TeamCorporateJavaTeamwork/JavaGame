package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage redPlayer;
    public static BufferedImage bluePlayer;
    public static BufferedImage puck;

    public static void init() {
        background = ImageLoader.loadImage("/Textures/hockey field.png");
        redPlayer = ImageLoader.loadImage("/Textures/red mallet.png");
        bluePlayer = ImageLoader.loadImage("/Textures/blue mallet.png");
        puck = ImageLoader.loadImage("/Textures/puck.png");
    }

    public static BufferedImage resizeImg(BufferedImage img, int newW, int newH)
    {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

}
