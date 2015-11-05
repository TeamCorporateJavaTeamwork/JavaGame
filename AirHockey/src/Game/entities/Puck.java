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
    private BoundingBox board;

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

        this.board=new BoundingBox(180+29,80+52,800-52,600-105);
    }

    public void tick() {

        this.posX += velocityX;
        this.posY += velocityY;

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

    }

    public void render(Graphics g) {
        g.drawImage(Assets.puck, this.posX, this.posY, null);
    }
}
