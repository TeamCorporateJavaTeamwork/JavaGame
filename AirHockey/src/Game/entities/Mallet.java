package Game.entities;

import Game.Game;
import gfx.Assets;

import java.awt.*;

public class Mallet{
    private String name;
    public int score;
    public int posX, posY;      //public because of bounding box check => make getter and setter
    public int radius;          //public because of bounding box check => make getter and setter
    private int weight;
    public int velocityX, velocityY; // constant for now
    private final int SPEED_LIMIT = 8;
    public BoundingBox gate;
    private BoundingBox board;
    public boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Mallet (String name, int posX, int posY,int field) {
        this.name = name;
        this.score = 0;

        this.posX = posX;
        this.posY = posY;

        this.velocityX = 5; // constant for now
        this.velocityY = 5; // constant for now

        this.radius = 49;
        this.weight = 5;

        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;
        this.isMovingUp = false;

        if(field ==1) {
            this.gate = new BoundingBox(180+30,80+190,0,410-190);
            this.board = new BoundingBox(180+29, 80+53, 372, 600-105); // startingpoint of background
        }
        else if(field ==2){
            this.gate = new BoundingBox(180+800-30,80+190,0,410 - 190);
            this.board = new BoundingBox(180+400, 80+53, 371, 600-105); //startingpoint of background + half the field
        }
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

                if (puckSpeedX > SPEED_LIMIT)
                    puckSpeedX = SPEED_LIMIT;
                if (puckSpeedY > SPEED_LIMIT)
                    puckSpeedY = SPEED_LIMIT;

                Game.puck.velocityX = (int)(puckSpeedX + ((1.05 * (radius + puckRadius) - dist)
                        *fx));
                Game.puck.velocityY = (int)(puckSpeedY + ((1.05 *(radius + puckRadius) - dist)
                        *fy));
            }
        }
        //collision with board
        //we define a circle by the most top left point of the smallest rectangle that can engulf the circle
        //if mallet X exceeds maxX allowed then we put it at the right border
        if(this.posX+2*radius>this.board.getRightX()){
            this.posX=this.board.getRightX()-2*radius-1;
        }
        //if mallet X is less than allowed - put mallet at left border;
        if(this.posX<this.board.getLeftX()){
            this.posX=this.board.getLeftX()+1;
        }
        // if mallet y is less than allowed( is above board) put it at top border;
        if(this.posY<this.board.getTopY()){
            this.posY=this.board.getTopY()+1;
        }
        //if mallet y is more than allowed ( is below board) put it at bottom border;
        if(this.posY+2*radius>this.board.getBottomY()){
            this.posY=this.board.getBottomY()-2*radius -1;
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

                if (puckSpeedX > SPEED_LIMIT)
                    puckSpeedX = SPEED_LIMIT;
                if (puckSpeedY > SPEED_LIMIT)
                    puckSpeedY = SPEED_LIMIT;

                Game.puck.velocityX = (int)(puckSpeedX + ((1.05 * (radius + puckRadius) - dist)
                        *fx));
                Game.puck.velocityY = (int)(puckSpeedY + ((1.05 *(radius + puckRadius) - dist)
                        *fy));
            }
        }
    }
    public void reset(Integer playerNum){
        if(playerNum==1){
            this.posX=250;
        }
        else if(playerNum ==2){
            this.posX=800;
        }
        this.posY=325;

        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;
        this.isMovingUp = false;

    }

    public void renderBlue(Graphics g) {
        g.drawImage(Assets.bluePlayer, this.posX, this.posY, null);
    }
    public void renderRed(Graphics g) {
        g.drawImage(Assets.redPlayer, this.posX, this.posY, null);
    }
}
