package Game.entities;

import gfx.Assets;

import java.awt.*;

public class Puck {
    private static int posX, posY, size, dirX, dirY, velocityX, velocityY, weight;
    private static final int SPEED_LIMIT = 5;
    private static boolean isMovingUp, isMovingLeft, isMovingDown, isMovingRight;

    //bounding box
    // will implement after custom bounding
    // class is finished

    public Puck() {
        this.posX = 510;
        this.posY = 315;

        this.dirX = 0;
        this.dirY = 0;

        this.velocityX = 0;
        this.velocityY = 0;

        this.weight = 5;
        this.size = 140;

        this.isMovingUp = false;
        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;

        //boundingbox to implement
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
        g.drawImage(Assets.resizeImg(Assets.puck, size, size), this.posX, this.posY, null);
    }
}
