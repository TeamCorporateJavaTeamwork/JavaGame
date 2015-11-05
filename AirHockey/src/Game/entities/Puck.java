package Game.entities;

import gfx.Assets;

import java.awt.*;

public class Puck {
    private static final int SPEED_LIMIT = 5;
    public static boolean isMovingUp, isMovingLeft, isMovingDown, isMovingRight;
    public  int velocityX;
    public  int velocityY;
    public static int radius;
    public static int posX, posY, weight;

    public Puck() {

        this.posX = 550;
        this.posY = 350;

        this.velocityX = 0;

        this.velocityY = 0;

        this.weight = 5;
        this.radius = 30;

        this.isMovingUp = false;
        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;

    }

    public void tick() {

        this.posX += velocityX;
        this.posY += velocityY;

    }

    public void render(Graphics g) {
        g.drawImage(Assets.puck, this.posX, this.posY, null);
    }
}
