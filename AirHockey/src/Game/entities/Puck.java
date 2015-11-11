package Game.entities;

import Game.GameEngine;
import gfx.Assets;
import states.StateManager;

import java.awt.*;
import java.util.Random;

public class Puck {
	private double velocityX;
	private double velocityY;
	private int radius;
    private int posX, posY;
    public BoundingBox board;
    private double wallFriction = 0.5D;
    private boolean redHasLost;
    private boolean roundStart;
    private boolean isInCorner,
            isInTopLeftCorner,
            isInBottomLeftCorner,
            isInTopRightCorner,
            isInBottomRightCorner;
    private boolean hasGoal;
    public Puck() {

        this.posX = 550;
        this.posY = 350;
        this.velocityX = 0;
        this.velocityY = 0;
        this.redHasLost = true;
        this.roundStart = true;
        this.radius = 30;
        this.hasGoal = false;

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
        if(this.posY < this.board.getTopY()){
            this.posY = this.board.getTopY() + 1;
            adjustFrictionY();
            velocityY = -velocityY;
        }
        //bottom
        if(this.posY + 2 * radius > this.board.getBottomY()){
            this.posY = this.board.getBottomY() - 2 * radius - 1;
            adjustFrictionY();
            velocityY = -velocityY;
        }
        //left
        if(this.posX <= this.board.getLeftX()){
            //if puck is between goalLine topY and bottomY -> reset game
            if((this.posY >= GameEngine.player1.getGate().getBox().getTopY() &&
                    this.posY <= GameEngine.player1.getGate().getBox().getBottomY()) ||
                    hasGoal){
                hasGoal = true;
                if (this.posX <= GameEngine.player1.getGate().getBox().getLeftX() - 90) {
                    GameEngine.player2.setScore(GameEngine.player2.getScore() + 1);
                    if(GameEngine.player2.getScore() == 7) {
                        GameEngine.tasks.victoryAnimation.start();
                        GameEngine.State.setState(StateManager.STATES.VICTORY);
                        return;
                    }
                    this.redHasLost = false;
                    this.roundStart = true;
                    hasGoal = false;
                    GameEngine.setShouldCountDown(true);
                    GameEngine.resetPositions();
                }
            } else if(this.posY <= GameEngine.player1.getGate().getBox().getTopY() ||
                    this.posY >= GameEngine.player1.getGate().getBox().getBottomY()) {
                //bounce off
                this.posX = this.board.getLeftX() + 1;
                adjustFrictionX();
                velocityX = -velocityX;
            }
        }
        //right
        if(this.posX + 2 * radius > this.board.getRightX()){
            //if puck is between goalLine topY and bottomY -> reset game
            if((this.posY >= GameEngine.player2.getGate().getBox().getTopY() &&
                    this.posY <= GameEngine.player2.getGate().getBox().getBottomY()) ||
                    hasGoal){
                hasGoal = true;
                if(this.posX + 2 * this.radius >= GameEngine.player2.getGate().getBox().getRightX() + 90) {
                    GameEngine.player1.setScore(GameEngine.player1.getScore() + 1);
                    if(GameEngine.player1.getScore() == 7) {
                        GameEngine.tasks.victoryAnimation.start();
                        GameEngine.State.setState(StateManager.STATES.VICTORY);
                        return;
                    }
                    this.redHasLost = true;
                    this.roundStart = true;
                    hasGoal = false;
                    GameEngine.setShouldCountDown(true);
                    GameEngine.resetPositions();
                }
                //game reset;
            } else if(this.posY <= GameEngine.player2.getGate().getBox().getTopY() ||
                    this.posY >= GameEngine.player2.getGate().getBox().getBottomY()){
                //bounce off
                this.posX=this.board.getRightX()-2*radius-1;
                adjustFrictionX();
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


    private void cornerCheck() {
        if (this.posY <= board.getTopY() + 2 * this.radius - 4
                && this.posX <= board.getLeftX() + 2 * this.radius - 4) {
            this.isInTopLeftCorner = true;
            this.isInCorner = true;
        } else if(this.posY + 2 * this.radius - 4 >= board.getBottomY() - 2 * this.radius + 4
                && this.posX <= board.getLeftX() + 2 * this.radius){
            this.isInBottomLeftCorner = true;
            this.isInCorner = true;
        } else if(this.posY <= board.getTopY() + 2 * this.radius - 4
                    && this.posX + 2 * this.radius >= board.getRightX() - 2 * this.radius) {
            this.isInTopRightCorner = true;
            this.isInCorner = true;
        } else if(this.posY + 2 * this.radius>= board.getBottomY() - 2 * this.radius
                    && this.posX + 2 * this.radius >= board.getRightX() - 2 * this.radius) {
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
        //normal puck
        g.drawImage(Assets.puck, this.posX, this.posY, null);
        //animated puck had incorrect image dimensions
    }

    public boolean isInCorner() {

        return isInCorner;
    }

    public boolean isInTopLeftCorner() {

        return isInTopLeftCorner;
    }

    public boolean isInBottomLeftCorner() {

        return isInBottomLeftCorner;
    }

    public boolean isInBottomRightCorner() {

        return isInBottomRightCorner;
    }

    public boolean isInTopRightCorner() {

        return isInTopRightCorner;
    }

    public int getPosX() {

        return posX;
    }

    public int getPosY() {

        return posY;
    }

	public int getRadius() {

		return radius;
	}

	public double getVelocityY() {

		return velocityY;
	}

	public double getVelocityX() {

		return velocityX;
	}

	public void setVelocityX(double velocityX) {

		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {

		this.velocityY = velocityY;
	}
}
