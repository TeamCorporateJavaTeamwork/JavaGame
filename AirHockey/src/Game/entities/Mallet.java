package Game.entities;

import gfx.Assets;

import java.awt.*;

public class Mallet{
    private String name;
    private int score;
    private int posX, posY;
    public int velocityX, velocityY; // constant for now
    public boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Mallet (String name, int posX, int posY) {
        this.name = name;
        this.score = 0;

        this.posX = posX;
        this.posY = posY;

        this.velocityX = 3; // constant for now
        this.velocityY = 3; // constant for now

        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;
        this.isMovingUp = false;
    }

    public void tick() {
        if (isMovingUp) {
            this.posY -= this.velocityY;
        }
        if (isMovingDown) {
            this.posY += this.velocityY;
        }
        if (isMovingRight) {
            this.posX += this.velocityX;
        }
        if (isMovingLeft) {
            this.posX -= this.velocityX;
        }
    }

    public void renderBlue(Graphics g) {
        g.drawImage(Assets.bluePlayer, this.posX, this.posY, null);
    }
    public void renderRed(Graphics g) {
        g.drawImage(Assets.redPlayer, this.posX, this.posY, null);
    }
}