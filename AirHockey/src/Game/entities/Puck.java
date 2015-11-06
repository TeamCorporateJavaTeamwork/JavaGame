package Game.entities;

import Game.Game;
import gfx.AnimationManager;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Puck {
    private static final int SPEED_LIMIT = 8;
    public static boolean isMovingUp, isMovingLeft, isMovingDown, isMovingRight;
    public  int velocityX;
    public  int velocityY;
    public static int radius;
    public static int posX, posY, weight;
    private BoundingBox board;

    private SpriteSheet puckAnimation;

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

        this.puckAnimation = new SpriteSheet(Assets.puckAnim, 100, 100);

        this.board=new BoundingBox(180+29,80+52,800-52,600-105);
    }

    public void tick() {
        //collision with board
        //we define a circle by the most top left point of the smallest rectangle that can engulf the circle
        //top
        if(this.posY<this.board.getTopY()){
            this.posY=this.board.getTopY()+1;
            if (velocityY>SPEED_LIMIT)
                velocityY = SPEED_LIMIT;
            if (velocityX>SPEED_LIMIT)
                velocityX = SPEED_LIMIT;
            velocityY = -velocityY;
        }
        //bottom
        if(this.posY+2*radius>this.board.getBottomY()){
            this.posY=this.board.getBottomY()-2*radius -1;
            if (velocityY>SPEED_LIMIT)
                velocityY = SPEED_LIMIT;
            if (velocityX>SPEED_LIMIT)
                velocityX = SPEED_LIMIT;
            velocityY = -velocityY;
        }
        //left
        if(this.posX<=this.board.getLeftX()){
            if(this.posY>=Game.player1.gate.getTopY() && this.posY<=Game.player1.gate.getBottomY()){
                Game.player2.score++;
                System.out.println(Game.player2.score);
                Game.resetPositions();
                //game reset;
            }
            else{
                this.posX=this.board.getLeftX()+1;
                if (velocityY>SPEED_LIMIT)
                    velocityY = SPEED_LIMIT;
                if (velocityX>SPEED_LIMIT)
                    velocityX = SPEED_LIMIT;
                velocityX = -velocityX;
            }
        }
        //right
        if(this.posX+2*radius>this.board.getRightX()){
            if(this.posY>=Game.player2.gate.getTopY() && this.posY<=Game.player2.gate.getBottomY()){
                Game.player1.score++;
                System.out.println(Game.player1.score);
                Game.resetPositions();
                //game reset;
            }
            else {
                this.posX=this.board.getRightX()-2*radius-1;
                if (velocityY>SPEED_LIMIT)
                    velocityY = SPEED_LIMIT;
                if (velocityX>SPEED_LIMIT)
                    velocityX = SPEED_LIMIT;
                velocityX = -velocityX;
            }
        }

        this.posX += velocityX;
        this.posY += velocityY;

    }

    public void reset(){
        this.posX = 550;
        this.posY = 350;

        this.velocityX = 0;
        this.velocityY = 0;

        this.isMovingUp = false;
        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;
    }

    public void render(Graphics g) {
        //animated puck
        g.drawImage(puckAnimation.crop(AnimationManager.puckAnimationPos, 0), this.posX - 20, this.posY - 20, null);
        //command to stop animation: AnimationManager.puckAnimationStop();

        //normal puck
        //g.drawImage(Assets.puck, this.posX, this.posY, null);
    }
}
