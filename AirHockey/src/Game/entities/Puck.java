package Game.entities;

import gfx.Assets;

import java.awt.*;

public class Puck {
    private static final int SPEED_LIMIT = 8;
    public static boolean hitUpperWall, hitDownWall, hitLeftWall, hitRightWall;
    public  int velocityX;
    public  int velocityY;
    public static int radius;
    public static int posX, posY, weight;
    private BoundingBox board;

    public Puck() {

        this.posX = 550;
        this.posY = 350;

        this.velocityX = 0;

        this.velocityY = 0;

        this.weight = 5;
        this.radius = 30;

        this.hitUpperWall = false;
        this.hitDownWall = false;
        this.hitLeftWall = false;
        this.hitRightWall = false;

        this.board=new BoundingBox(180+29,80+52,800-52,600-105);
    }

    public void tick() {

        if(hitUpperWall) {
            if (velocityY>SPEED_LIMIT)
                velocityY = SPEED_LIMIT;
            if (velocityX>SPEED_LIMIT)
                velocityX = SPEED_LIMIT;
            velocityY = -velocityY;
            hitUpperWall = false;
        }
        if(hitDownWall) {
            if (velocityY>SPEED_LIMIT)
                velocityY = SPEED_LIMIT;
            if (velocityX>SPEED_LIMIT)
                velocityX = SPEED_LIMIT;
            velocityY = -velocityY;
            hitDownWall = false;
        }
        if(hitLeftWall) {
            if (velocityY>SPEED_LIMIT)
                velocityY = SPEED_LIMIT;
            if (velocityX>SPEED_LIMIT)
                velocityX = SPEED_LIMIT;
            velocityX = -velocityX;
            hitLeftWall = false;
        }
        if(hitRightWall) {
            if (velocityY>SPEED_LIMIT)
                velocityY = SPEED_LIMIT;
            if (velocityX>SPEED_LIMIT)
                velocityX = SPEED_LIMIT;
            velocityX = -velocityX;
            hitRightWall = false;
        }

        this.posX += velocityX;
        this.posY += velocityY;

        //collision with board
        //we define a circle by the most top left point of the smallest rectangle that can engulf the circle
        //if mallet X exceeds maxX allowed then we put it at the right border
        if(this.posX+2*radius>this.board.getRightX()){
            this.posX=this.board.getRightX()-2*radius-1;
            this.hitRightWall = true;
        }
        //if mallet X is less than allowed - put mallet at left border;
        if(this.posX<this.board.getLeftX()){
            this.posX=this.board.getLeftX()+1;
            this.hitLeftWall = true;
        }
        // if mallet y is less than allowed( is above board) put it at top border;
        if(this.posY<this.board.getTopY()){
            this.posY=this.board.getTopY()+1;
            this.hitUpperWall = true;
        }
        //if mallet y is more than allowed ( is below board) put it at bottom border;
        if(this.posY+2*radius>this.board.getBottomY()){
            this.posY=this.board.getBottomY()-2*radius -1;
            this.hitDownWall = true;
        }

    }

    public void render(Graphics g) {
        g.drawImage(Assets.puck, this.posX, this.posY, null);
    }
}
