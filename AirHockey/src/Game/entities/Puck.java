package Game.entities;

import gfx.Assets;

import java.awt.*;

public class Puck {
    private static int posX, posY, radius, weight;
    private static final int SPEED_LIMIT = 5;
    public static boolean isMovingUp, isMovingLeft, isMovingDown, isMovingRight;
    public static int velocityX;
    public static int velocityY;

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
        //to implement the movement after input handling

        if (isMovingUp) {
            this.posY -= velocityY;
        }
        if (isMovingLeft) {
            this.posX -= velocityX;
        }
        if (isMovingDown) {
            this.posY += velocityY;
        }
        if (isMovingRight) {
            this.posX += velocityX;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Assets.puck, this.posX, this.posY, null);
    }
}
