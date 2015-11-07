package Game.entities;

import Game.Game;
import Game.tasks.TaskManager;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.Random;

//friction
//random dir on spawn towards loser

public class Puck {
    private static final int SPEED_LIMIT = 12;
    public  double velocityX;
    public  double velocityY;
    public static int radius;
    public static int posX, posY, weight;
    private BoundingBox board;
    private double wallFriction = 0.75D;
    private boolean redHasLost;
    private boolean roundStart;

    private SpriteSheet puckAnimation;

    public Puck() {

        this.posX = 550;
        this.posY = 350;

        this.velocityX = 0;

        this.velocityY = 0;

        this.redHasLost = true;
        this.roundStart = true;

        this.weight = 5;
        this.radius = 30;

        this.puckAnimation = new SpriteSheet(Assets.puckAnim, 100, 100);

        this.board=new BoundingBox(180+29,80+52,800-52,600-105);
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

        //collision with board
        //we define a circle by the most top left point of the smallest rectangle that can engulf the circle
        //top
        if(this.posY<this.board.getTopY()){
            this.posY=this.board.getTopY()+1;
            if (velocityY < 0) {
                velocityY += wallFriction;
            } else {
                velocityY -= wallFriction;
            }
            if (Math.abs(velocityY)>SPEED_LIMIT) {
                if (velocityY < 0) {
                    velocityY = -SPEED_LIMIT;
                } else {
                    velocityY = SPEED_LIMIT;
                }
            }
            if (Math.abs(velocityX)>SPEED_LIMIT) {
                if (velocityX < 0) {
                    velocityX = -SPEED_LIMIT;
                } else {
                    velocityX = SPEED_LIMIT;
                }
            }
            velocityY = -velocityY;
        }
        //bottom
        if(this.posY+2*radius>this.board.getBottomY()){
            this.posY=this.board.getBottomY()-2*radius -1;
            if (velocityY < 0) {
                velocityY += wallFriction;
            } else {
                velocityY -= wallFriction;
            }
            if (Math.abs(velocityY)>SPEED_LIMIT) {
                if (velocityY < 0) {
                    velocityY = -SPEED_LIMIT;
                } else {
                    velocityY = SPEED_LIMIT;
                }
            }
            if (Math.abs(velocityX)>SPEED_LIMIT) {
                if (velocityX < 0) {
                    velocityX = -SPEED_LIMIT;
                } else {
                    velocityX = SPEED_LIMIT;
                }
            }
            velocityY = -velocityY;
        }
        //left
        if(this.posX<=this.board.getLeftX()){
            //if puck is between goalLine topY and bottomY -> reset game
            if(this.posY >= Game.player1.gate.getTopY() && this.posY <= Game.player1.gate.getBottomY()){
                Game.player2.setScore(Game.player2.getScore() + 1);
                this.redHasLost = false;
                this.roundStart = true;
                System.out.println(Game.player2.getScore());
                Game.resetPositions();
            }
            //else -> bounce off
            else{
                this.posX=this.board.getLeftX()+1;
                if (Math.abs(velocityY)>SPEED_LIMIT) {
                    if (velocityY < 0) {
                        velocityY = -SPEED_LIMIT;
                    } else {
                        velocityY = SPEED_LIMIT;
                    }
                }
                if (velocityX < 0) {
                    velocityX += wallFriction;
                } else {
                    velocityX -= wallFriction;
                }
                if (Math.abs(velocityX)>SPEED_LIMIT) {
                    if (velocityX < 0) {
                        velocityX = -SPEED_LIMIT;
                    } else {
                        velocityX = SPEED_LIMIT;
                    }
                }
                velocityX = -velocityX;
            }
        }
        //right
        if(this.posX+2*radius>this.board.getRightX()){
            //if puck is between goalLine topY and bottomY -> reset game
            if(this.posY>=Game.player2.gate.getTopY() && this.posY<=Game.player2.gate.getBottomY()){
                Game.player1.setScore(Game.player1.getScore() + 1);
                this.redHasLost = true;
                this.roundStart = true;
                System.out.println(Game.player1.getScore());
                Game.resetPositions();
                //game reset;
            }
            //else -> bounce off
            else {
                this.posX=this.board.getRightX()-2*radius-1;
                if (Math.abs(velocityY)>SPEED_LIMIT)
                    if (velocityY < 0) {
                        velocityY = -SPEED_LIMIT;
                    } else {
                        velocityY = SPEED_LIMIT;
                    }
                if (velocityX < 0) {
                    velocityX += wallFriction;
                } else {
                    velocityX -= wallFriction;
                }
                if (Math.abs(velocityX)>SPEED_LIMIT)
                    if (velocityX < 0) {
                        velocityX = -SPEED_LIMIT;
                    } else {
                        velocityX = SPEED_LIMIT;
                    }
                velocityX = -velocityX;
            }
        }

        this.posX += velocityX;
        this.posY += velocityY;

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

        //normal puck
        //g.drawImage(Assets.puck, this.posX, this.posY, null);
    }
}
