package Game.entities;

import Game.Game;
import gfx.Assets;
import gfx.ImageColorizer;

import java.awt.*;

public class Mallet{
    private float posX, posY;
    private int radius;
    private int weight;
    public float velocityX, velocityY;
    public float slideLevelX = 0.4f;
    public float slideLevelY = 0.4f;
    public float slideOpposition = -0.05f;

    private BoundingBox board;

    public boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Mallet (int posX, int posY,int field) {

        this.posX = posX;
        this.posY = posY;

        this.velocityX = 0;
        this.velocityY = 0;

        this.radius = 49;
        this.weight = 10;

        if(field == 1) {
            this.board = new BoundingBox(180+29, 80+53, 372, 600-2*53);
        } else if(field == 2) {
            this.board = new BoundingBox(180+400, 80+53, 372, 600-2*53);
        }
    }

    public void tick() {
        move();
	    addOpposistion();
        calculateSlideLevels();
	    calculateVelocity();
	    collisionChecks();

    }

	private void collisionChecks() {

		int puckRadius = Game.puck.radius;
		int puckX = Game.puck.posX + puckRadius;
		int puckY = Game.puck.posY + puckRadius;
		double puckSpeedX = Game.puck.velocityX;
		double puckSpeedY = Game.puck.velocityY;
		float playerX = Game.player1.getMallet().posX + radius;
		float playerY = Game.player1.getMallet().posY + radius;

		checkCollision(playerX, playerY, puckX, puckY, puckRadius, puckSpeedX, puckSpeedY);

		boardCollision();

		puckSpeedX = Game.puck.velocityX;
		puckSpeedY = Game.puck.velocityY;
		playerX = Game.player2.getMallet().posX + radius;
		playerY = Game.player2.getMallet().posY + radius;

		checkCollision(playerX, playerY, puckX, puckY, puckRadius, puckSpeedX, puckSpeedY);

	}

	private void boardCollision() {
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
	}

	private void checkCollision(float playerX, float playerY, int puckX, int puckY, int puckRadius, double puckSpeedX, double puckSpeedY) {
		if (isColliding(playerX, playerY, puckX, puckY, puckRadius)) {
			double distance = calculateDistance(playerX, playerY, puckX, puckY);

			if (distance < radius + puckRadius) {

				double dx, dy, fx, fy;

				dx = puckX - playerX; // distance between centers in x
				dy = puckY - playerY; // distance between centers in y

				// define unit-length vector (fx, fy) in direction of the force
				double dist = Math.sqrt(dx*dx + dy*dy); // norm of (dx, dy)
				fx = dx/dist;
				fy = dy/dist;

				Game.puck.velocityX = (int)(puckSpeedX + ((radius + puckRadius) - dist) * fx);
				Game.puck.velocityY = (int)(puckSpeedY + ((radius + puckRadius) - dist) * fy);
			}
		}

	}

	private double calculateDistance(float playerX, float playerY, int puckX, int puckY) {
		return Math.sqrt(((playerX - puckX) * (playerX - puckX) +
				(playerY - puckY) * (playerY - puckY)));
	}

	private boolean isColliding(float playerX, float playerY, int puckX, int puckY, int puckRadius) {
		return playerX + this.radius + puckRadius > puckX &&
				playerX < puckX + this.radius + puckRadius &&
				playerY + radius + puckRadius > puckY
				&& playerY < puckY + radius + puckRadius;
	}
	private void move() {
        //Moving X and Y with the velocity
		this.posY += this.velocityY;
		this.posX += this.velocityX;
	}

	private void addOpposistion() {
        //add opposition
		this.velocityX += this.velocityX * slideOpposition;
		this.velocityY += this.velocityY * slideOpposition;

	}

	private void calculateVelocity() {
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
	}

	private void calculateSlideLevels() {
		if(Math.abs(this.velocityX) > 3f) {
			this.slideLevelX = 0.50f;
		} else if(Math.abs(this.velocityX) > 2f) {
			this.slideLevelX = 0.45f;
		} else {
			this.slideLevelX = 0.4f;
		}

		if(Math.abs(this.velocityY) > 3f) {
			this.slideLevelY = 0.45f;
		} else if (Math.abs(this.velocityY) > 2f) {
			this.slideLevelY = 0.40f;
		} else {
			this.slideLevelY = 0.35f;
		}
	}

	public void reset(int playerNum){
        if(playerNum == 1){
            this.posX=250;
        }
        else if(playerNum == 2){
            this.posX=800;
        }
        this.posY=325;

        this.isMovingDown = false;
        this.isMovingLeft = false;
        this.isMovingRight = false;
        this.isMovingUp = false;

    }

    public void renderLeft(Graphics g) {
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, new Color(0, 0, 255, 175)), Math.round(this.posX), Math.round(this.posY), null);
    }
    public void renderRight(Graphics g) {
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, new Color(255, 0, 0, 175)), Math.round(this.posX), Math.round(this.posY), null);
    }

}
