package Game.entities;

import Game.Game;
import gfx.Assets;

import java.awt.*;

public class Mallet{
    private String name;
    public int score;

    private float posX, posY;
    private int radius;
    private int weight;
    public float velocityX, velocityY;
    public float slideLevelX = 0.4f;
    public float slideLevelY = 0.4f;
    public float slideOpposition = -0.05f;
    private final int SPEED_LIMIT = 6;

    public BoundingBox gate;
    private BoundingBox board;

    public boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Mallet (String name, int posX, int posY,int field) {
        this.name = name;
        this.score = 0;

        this.posX = posX;
        this.posY = posY;

        this.velocityX = 0;
        this.velocityY = 0;

        this.radius = 49;
        this.weight = 5;

        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;
        this.isMovingUp = false;

        if(field ==1) {
                                     //(starting point of bgX + left padding,start p of bgY + top padding(topY), width, height
            this.gate = new BoundingBox(180+30,80+190,0,405-190);
                                    //(starting point of bgX + left padding,start p of bgY + top padding(topY), width(to center of field), boxHeight
            this.board = new BoundingBox(180+29, 80+53, 372, 600-2*53);
        }
        else if(field ==2){
            this.gate = new BoundingBox(180+800-30,80+190,0,405 - 190);
            this.board = new BoundingBox(180+400, 80+53, 371, 600-2*53);
        }
    }

    public void tick() {
        //Moving X and Y with the velocity
        this.posY += this.velocityY;
        this.posX += this.velocityX;
        //add opposition
        this.velocityX += this.velocityX * slideOpposition;
        this.velocityY += this.velocityY * slideOpposition;

        if(Math.abs(this.velocityX) > 3f) {
            slideLevelX = 0.5f;
        } else if(Math.abs(this.velocityX) > 2f) {
            slideLevelX = 0.45f;
        } else {
            slideLevelX = 0.4f;
        }

        if(Math.abs(this.velocityY) > 3f) {
            slideLevelY = 0.5f;
        } else if (Math.abs(this.velocityY) > 2f) {
            slideLevelY = 0.45f;
        } else {
            slideLevelY = 0.4f;
        }

        if (isMovingUp) {
            this.velocityY -= slideLevelY;
        }
        if (isMovingDown) {
            this.velocityY += slideLevelY;
        }
        if (isMovingRight) {
            this.velocityX += slideLevelX;
        }
        if (isMovingLeft) {
            this.velocityX -= slideLevelX;
        }

        int puckRadius = Game.puck.radius;
        int puckX = Game.puck.posX + puckRadius;
        int puckY = Game.puck.posY + puckRadius;
        int puckSpeedX = Game.puck.velocityX;
        int puckSpeedY = Game.puck.velocityY;
        float playerX = Game.player1.posX + radius;
        float playerY = Game.player1.posY + radius;

        if (playerX + radius + puckRadius > puckX &&
                playerX < puckX + radius + puckRadius &&
                playerY + radius + puckRadius > puckY
                && playerY < puckY + radius + puckRadius) {
            double distance = Math.sqrt(((playerX - puckX) * (playerX - puckX) +
                    (playerY - puckY) * (playerY - puckY)));

            if (distance < radius + puckRadius) {
                //Debugging comments ->>
                //System.out.println("Balls collided");
                //System.out.println("mallet BLUE X, Y:" + playerX + " " + playerY);
                //System.out.println("puck X, Y:" + puckX + " " + puckY);

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
            this.velocityX = -0.5f; //if mallet hit right border to bounce
        }
        //if mallet X is less than allowed - put mallet at left border;
        if(this.posX<this.board.getLeftX()){
            this.posX=this.board.getLeftX()+1;
            this.velocityX = 0.5f; //if mallet hit left border to bounce
        }
        // if mallet y is less than allowed( is above board) put it at top border;
        if(this.posY<this.board.getTopY()){
            this.posY=this.board.getTopY()+1;
            this.velocityY = 0.5f; //if mallet hit top border to bounce
        }
        //if mallet y is more than allowed ( is below board) put it at bottom border;
        if(this.posY+2*radius>this.board.getBottomY()){
            this.posY=this.board.getBottomY()-2*radius -1;
            this.velocityY = -0.5f; //if mallet hit bottom border to bounce
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
                //Debugging comments ->>
//                System.out.println("Balls collided");
//                System.out.println("mallet RED X, Y:" + playerX + " " + playerY);
//                System.out.println("puck X, Y:" + puckX + " " + puckY);

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
        g.drawImage(Assets.bluePlayer, Math.round(this.posX), Math.round(this.posY), null);
    }
    public void renderRed(Graphics g) {
        g.drawImage(Assets.redPlayer, Math.round(this.posX), Math.round(this.posY), null);
    }
}
