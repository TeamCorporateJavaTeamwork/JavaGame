package Game.entities;

import Game.Game;
import Game.tasks.TaskManager;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.Random;

public class Puck {
    private static final int SPEED_LIMIT = 12;
    public  double velocityX;
    public  double velocityY;
    public static int radius;
    public static int posX, posY;
    public BoundingBox board;
    private double wallFriction = 0.75D;
    private boolean redHasLost;
    private boolean roundStart;
    public static boolean isInCorner, isInTopLeftCorner, isInBottomLeftCorner, isInTopRightCorner,
            isInBottomRightCorner;

    private SpriteSheet puckAnimation;

    public Puck() {

        this.posX = 550;
        this.posY = 350;
        this.velocityX = 0;
        this.velocityY = 0;
        this.redHasLost = true;
        this.roundStart = true;
        this.radius = 30;

        this.isInCorner = false;
        this.isInTopLeftCorner = false;
        this.isInBottomLeftCorner = false;
        this.isInTopRightCorner = false;
        this.isInBottomRightCorner = false;

        this.puckAnimation = new SpriteSheet(Assets.puckAnim, 100, 100);
        this.board = new BoundingBox(180+29,80+52,800-52,600-105);
    }

    //default is towards red
    private void spawnPuckOnLoserSide() {
        Random r = new Random();
        int x = r.nextInt(3 - 2 + 1) + 2;
        int y = r.nextInt(2) + 1;
        int dir = r.nextInt(2 - 1 + 1 ) + 1;
        if (redHasLost) {
            velocityX = x;
            if (dir == 1) {
                velocityY = -y;
            } else {
                velocityY = y;
            }
        } else {
            velocityX = -x;
            if (dir == 1) {
                velocityY = -y;
            } else {
                velocityY = y;
            }
        }
    }

    public void tick() {
        if (roundStart) {
            spawnPuckOnLoserSide();
            roundStart = false;
        }

        calcBoardCollisionsAndAngles();

        this.posX += velocityX;
        this.posY += velocityY;

        cornerCheck();
    }

    private void calcBoardCollisionsAndAngles() {
        //collision with board
        //we define a circle by the most top left point of the smallest rectangle that can engulf the circle
        //top
        if(this.posY<this.board.getTopY()){
            this.posY=this.board.getTopY()+1;
            adjustFrictionY();
            controlSpeedY();
            controlSpeedX();
            velocityY = -velocityY;
        }
        //bottom
        if(this.posY+2*radius>this.board.getBottomY()){
            this.posY=this.board.getBottomY()-2*radius -1;
            adjustFrictionY();
            controlSpeedY();
            controlSpeedX();
            velocityY = -velocityY;
        }
        //left
        if(this.posX<=this.board.getLeftX()){
            //if puck is between goalLine topY and bottomY -> reset game
            if(this.posY >= Game.player1.getGate().getBox().getTopY() && this.posY <= Game.player1.getGate().getBox().getBottomY()){
                Game.player2.setScore(Game.player2.getScore() + 1);
                this.redHasLost = false;
                this.roundStart = true;
                Game.resetPositions();
            }
            //else -> bounce off
            else{
                this.posX=this.board.getLeftX()+1;
                adjustFrictionX();
                controlSpeedY();
                controlSpeedX();
                velocityX = -velocityX;
            }
        }
        //right
        if(this.posX+2*radius>this.board.getRightX()){
            //if puck is between goalLine topY and bottomY -> reset game
            if(this.posY >= Game.player2.getGate().getBox().getTopY() && this.posY <= Game.player2.getGate().getBox().getBottomY()){
                Game.player1.setScore(Game.player1.getScore() + 1);
                this.redHasLost = true;
                this.roundStart = true;
                Game.resetPositions();
                //game reset;
            }
            //else -> bounce off
            else {
                this.posX=this.board.getRightX()-2*radius-1;
                adjustFrictionX();
                controlSpeedY();
                controlSpeedX();
                velocityX = -velocityX;
            }
        }
    }

    private void adjustFrictionY() {
        if (velocityY < 0) {
            velocityY += wallFriction;
        } else {
            velocityY -= wallFriction;
        }
    }

    private void adjustFrictionX() {
        if (velocityX < 0) {
            velocityX += wallFriction;
        } else {
            velocityX -= wallFriction;
        }
    }

    private void controlSpeedX() {
        if (Math.abs(velocityX)>SPEED_LIMIT)
            if (velocityX < 0) {
                velocityX = -SPEED_LIMIT;
            } else {
                velocityX = SPEED_LIMIT;
            }
    }

    private void controlSpeedY() {
        if (Math.abs(velocityY)>SPEED_LIMIT) {
            if (velocityY < 0) {
                velocityY = -SPEED_LIMIT;
            } else {
                velocityY = SPEED_LIMIT;
            }
        }
    }

    private void cornerCheck() {
        if (this.posY <= board.getTopY() + 2*this.radius && this.posX <= board.getLeftX() + 2*this.radius) {
            this.isInTopLeftCorner = true;
            this.isInCorner = true;
        } else if(this.posY + 2*this.radius >= board.getBottomY() - 2*this.radius && this.posX <=
                board
                        .getLeftX()
                        + 2*this.radius){
            this.isInBottomLeftCorner = true;
            this.isInCorner = true;
        } else if(this.posY <= board.getTopY() + 2*this.radius && this.posX +
                2*this.radius >= board.getRightX() - 2*this.radius) {
            this.isInTopRightCorner = true;
            this.isInCorner = true;
        } else if(this.posY + 2*this.radius >= board.getBottomY() - 2*this.radius && this.posX +
                2*this.radius >=
                board.getRightX() - 2*this.radius) {
            this.isInBottomRightCorner = true;
            this.isInCorner = true;
        } else {
            this.isInTopLeftCorner = false;
            this.isInTopRightCorner = false;
            this.isInBottomRightCorner = false;
            this.isInBottomLeftCorner = false;
            this.isInCorner = false;
        }
    }

    public void reset(){
        //reset position
        this.posX = 550;
        this.posY = 350;
    }

    public void render(Graphics g) {
        //animated puck
        g.drawImage(puckAnimation.crop(TaskManager.puckAnimation.position, 0), this.posX - 20, this.posY - 20, null);
        //command to stop animation: TaskManager.puckAnimationStop();

    }
}
