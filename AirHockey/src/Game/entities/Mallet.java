package Game.entities;

import Game.Game;
import gfx.Assets;

import java.awt.*;

public class Mallet{
    private String name;
    private int score;
    private int posX, posY;
    private int radius;
    private int weight;
    public int velocityX, velocityY; // constant for now
    private final int SPEED_LIMIT = 6;
    public boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Mallet (String name, int posX, int posY) {
        this.name = name;
        this.score = 0;

        this.posX = posX;
        this.posY = posY;

        this.velocityX = 3; // constant for now
        this.velocityY = 3; // constant for now

        this.radius = 49;
        this.weight = 5;

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

        int puckRadius = Game.puck.radius;
        int puckX = Game.puck.posX + puckRadius;
        int puckY = Game.puck.posY + puckRadius;
        int puckSpeedX = Game.puck.velocityX;
        int puckSpeedY = Game.puck.velocityY;
        int playerX = Game.player1.posX + radius;
        int playerY = Game.player1.posY + radius;

        if (playerX + radius + puckRadius > puckX &&
                playerX < puckX + radius + puckRadius &&
                playerY + radius + puckRadius > puckY
                && playerY < puckY + radius + puckRadius) {
            double distance = Math.sqrt(((playerX - puckX) * (playerX - puckX) +
                    (playerY - puckY) * (playerY - puckY)));

            if (distance < radius + puckRadius) {
                System.out.println("Balls collided");
                System.out.println("mallet BLUE X, Y:" + playerX + " " + playerY);
                System.out.println("puck X, Y:" + puckX + " " + puckY);

                double dx, dy, fx, fy;

                dx = puckX - playerX; // distance between centers in x
                dy = puckY - playerY; // distance between centers in y

                // define unit-length vector (fx, fy) in direction of the force
                double dist = Math.sqrt(dx*dx + dy*dy); // norm of (dx, dy)
                fx = dx/dist;
                fy = dy/dist;

                Game.puck.velocityX = (int)(puckSpeedX + ((1.05 * (radius + puckRadius) - dist)
                        *fx));
                Game.puck.velocityY = (int)(puckSpeedY + ((1.05 *(radius + puckRadius) - dist)
                        *fy));
            }
        }

        puckSpeedX = Game.puck.velocityX;
        puckSpeedY = Game.puck.velocityY;
        playerX = Game.player2.posX + radius;
        playerY = Game.player2.posY + radius;

        if (playerX + radius + puckRadius > puckX &&
                playerX < puckX + radius + puckRadius &&
                playerY + radius + puckRadius > puckY
                && playerY < puckY + radius + puckRadius) {
            double distance = Math.sqrt(((playerX - puckX) * (playerX - puckX) +
                    (playerY - puckY) * (playerY - puckY)));

            if (distance < radius + puckRadius) {
                System.out.println("Balls collided");
                System.out.println("mallet RED X, Y:" + playerX + " " + playerY);
                System.out.println("puck X, Y:" + puckX + " " + puckY);

                double dx, dy, fx, fy;

                dx = puckX - playerX; // distance between centers in x
                dy = puckY - playerY; // distance between centers in y

                // define unit-length vector (fx, fy) in direction of the force
                double dist = Math.sqrt(dx*dx + dy*dy); // norm of (dx, dy)
                fx = dx/dist;
                fy = dy/dist;

                Game.puck.velocityX = (int)(puckSpeedX + ((1.05 * (radius + puckRadius) - dist)
                        *fx));
                Game.puck.velocityY = (int)(puckSpeedY + ((1.05 *(radius + puckRadius) - dist)
                        *fy));
            }
        }
    }

    public void renderBlue(Graphics g) {
        g.drawImage(Assets.bluePlayer, this.posX, this.posY, null);
    }
    public void renderRed(Graphics g) {
        g.drawImage(Assets.redPlayer, this.posX, this.posY, null);
    }
}
