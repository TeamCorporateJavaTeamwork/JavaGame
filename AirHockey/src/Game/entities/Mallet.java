package Game.entities;

import Game.GameEngine;
import gfx.Assets;
import gfx.ImageColorizer;

import java.awt.*;

public class Mallet{
    private float posX, posY;
    private int radius;
    private float velocityX, velocityY;
    private float slideLevelX = 0.5f;
    private float slideLevelY = 0.5f;
    private float slideOpposition = -0.07f;
	private final int SPEED_LIMIT_PUCK = 10;
//	private final int SPEED_LIMIT_MALLET = 11;

    private BoundingBox board;

    public boolean isMovingUp, isMovingDown, isMovingLeft, isMovingRight;

    public Mallet (int posX, int posY,int field) {

        this.posX = posX;
        this.posY = posY;

        this.velocityX = 0;
        this.velocityY = 0;

        this.radius = 49;

        if(field == 1) {
            this.board = new BoundingBox(180+29, 80+53, 372, 600-2*53);
        } else if(field == 2) {
            this.board = new BoundingBox(180+400, 80+53, 372, 600-2*53);
        }
    }

    public void tick() {
	    addOpposition();
        calculateSlideLevels();
	    calculateVelocity();
	    collisionChecks();
		move();
    }

	private void collisionChecks() {

		int puckRadius = GameEngine.puck.getRadius();
		int puckX = GameEngine.puck.getPosX() + puckRadius;
		int puckY = GameEngine.puck.getPosY() + puckRadius;
		double puckSpeedX = GameEngine.puck.getVelocityX();
		double puckSpeedY = GameEngine.puck.getVelocityY();
		float playerX = GameEngine.player1.getMallet().posX + radius;
		float playerY = GameEngine.player1.getMallet().posY + radius;

		checkCollision(playerX, playerY, puckX, puckY, puckRadius, puckSpeedX, puckSpeedY);

		boardCollision();

		puckSpeedX = GameEngine.puck.getVelocityX();
		puckSpeedY = GameEngine.puck.getVelocityY();
		playerX = GameEngine.player2.getMallet().posX + radius;
		playerY = GameEngine.player2.getMallet().posY + radius;

		checkCollision(playerX, playerY, puckX, puckY, puckRadius, puckSpeedX, puckSpeedY);

	}

	private void boardCollision() {
        //collision with board
		//we define a circle by the most top left point of the smallest rectangle that can engulf the circle
		//if mallet X exceeds maxX allowed then we put it at the right border
		if(this.posX + 2 * radius > this.board.getRightX()){
			this.posX = this.board.getRightX() - 2 * radius-1;
			this.velocityX = -0.5f; //if mallet hit right border to bounce
		}
		//if mallet X is less than allowed - put mallet at left border;
		if(this.posX<this.board.getLeftX()){
			this.posX=this.board.getLeftX() + 1;
			this.velocityX = 0.5f; //if mallet hit left border to bounce
		}
		// if mallet y is less than allowed( is above board) put it at top border;
		if(this.posY < this.board.getTopY()){
			this.posY = this.board.getTopY() + 1;
			this.velocityY = 0.5f; //if mallet hit top border to bounce
		}
		//if mallet y is more than allowed ( is below board) put it at bottom border;
		if(this.posY + 2 * radius > this.board.getBottomY()){
			this.posY = this.board.getBottomY() - 2 * radius - 1;
			this.velocityY = -0.5f; //if mallet hit bottom border to bounce
		}
	}

	private void checkCollision(float playerX, float playerY, int puckX, int puckY, int puckRadius, double puckSpeedX, double puckSpeedY) {
		if (hasPotentialOfColliding(playerX, playerY, puckX, puckY, puckRadius)) {
			double distance = calculateDistance(playerX, playerY, puckX, puckY);

			//Check if actual collision occurred
			if (distance < radius + puckRadius) {

				double dx, dy, fx, fy;

				dx = puckX - playerX; // distance between centers in x
				dy = puckY - playerY; // distance between centers in y

				// define unit-length vector (fx, fy) in direction of the force
				double dist = Math.sqrt(dx * dx + dy * dy); // norm of (dx, dy)
				fx = dx / dist;
				fy = dy / dist;

				GameEngine.puck.setVelocityX((int) (puckSpeedX + ((radius + puckRadius) - dist) * fx));
				GameEngine.puck.setVelocityY((int) (puckSpeedY + ((radius + puckRadius) - dist) * fy));

				if(Math.abs(GameEngine.puck.getVelocityX()) > SPEED_LIMIT_PUCK) {
					if(GameEngine.puck.getVelocityX() < 0) {
						GameEngine.puck.setVelocityX(-SPEED_LIMIT_PUCK);
					} else {
						GameEngine.puck.setVelocityX(SPEED_LIMIT_PUCK);
					}
				}
				if(Math.abs(GameEngine.puck.getVelocityY()) > SPEED_LIMIT_PUCK) {
					if(GameEngine.puck.getVelocityY() < 0) {
						GameEngine.puck.setVelocityY(-SPEED_LIMIT_PUCK);
					} else {
						GameEngine.puck.setVelocityY(SPEED_LIMIT_PUCK);
					}
				}
			}
		}
	}

	private double calculateDistance(float playerX, float playerY, int puckX, int puckY) {
		return Math.sqrt(((playerX - puckX) * (playerX - puckX) +
				(playerY - puckY) * (playerY - puckY)));
	}

	private boolean hasPotentialOfColliding(float playerX, float playerY, int puckX, int puckY, int
			puckRadius) {
		//Axis-Aligned BoundingBoxCheck
		return playerX + this.radius + puckRadius > puckX &&
				playerX < puckX + this.radius + puckRadius &&
				playerY + radius + puckRadius > puckY
				&& playerY < puckY + radius + puckRadius;
	}
	private void move() {
		//New Moving Method -> Have Other Minor Bugs -> Code is unclean

		//I added this piece of code but it's commented because:
		//It is useful but very heavy check for every tick -> game runs a lot smoother without it
		//If we want to be completely realistic we should do it but in my opinion game runs way
		//better without it, yet I leave the code to be uncommented if needed.

//		if(this.velocityX > SPEED_LIMIT_MALLET) {
//			if(this.velocityX < 0) {
//				this.velocityX = -SPEED_LIMIT_MALLET;
//			} else {
//				this.velocityX = SPEED_LIMIT_MALLET;
//			}
//		}
//		if(this.velocityY > SPEED_LIMIT_MALLET) {
//			if(this.velocityY < 0) {
//				this.velocityY = -SPEED_LIMIT_MALLET;
//			} else {
//				this.velocityY = SPEED_LIMIT_MALLET;
//			}
//		}

		if(GameEngine.puck.isInCorner()) {
			if (GameEngine.puck.isInTopLeftCorner()) {
				if (this.posY <= GameEngine.puck.board.getTopY() + 2 * GameEngine.puck.getRadius()
						&& this.posX <= GameEngine.puck.board.getLeftX() + 2 * GameEngine.puck.getRadius()) {
					GameEngine.puck.setVelocityX(1);
					GameEngine.puck.setVelocityY(1);
					this.posX += 0.5f;
					this.posY += 0.5f;
				} else {
					this.posX += this.velocityX;
					this.posY += this.velocityY;
				}
			} else if (GameEngine.puck.isInBottomLeftCorner()) {
				if (this.posY + 2 * this.radius >= GameEngine.puck.board.getBottomY() - 2 * GameEngine.puck.getRadius()
						&& this.posX <= GameEngine.puck.board.getLeftX() + 2 * GameEngine.puck.getRadius()) {
					GameEngine.puck.setVelocityX(1);
					GameEngine.puck.setVelocityY(-1);
					this.posX += 0.5f;
					this.posY -= 0.5f;
				} else {
					this.posX += this.velocityX;
					this.posY += this.velocityY;
				}
			} else if (GameEngine.puck.isInTopRightCorner()) {
				if (this.posY <= GameEngine.puck.board.getTopY() + 2 * GameEngine.puck.getRadius() - 4
						&& this.posX + 2 * this.radius >= GameEngine.puck.board.getRightX() - 2 * GameEngine.puck.getRadius() + 4) {
					GameEngine.puck.setVelocityX(-1);
					GameEngine.puck.setVelocityY(1);
					this.posX -= 0.5f;
					this.posY += 0.5f;
				} else {
					this.posX += this.velocityX;
					this.posY += this.velocityY;
				}
			} else if (GameEngine.puck.isInBottomRightCorner()) {
				if (this.posY + 2*this.radius >= GameEngine.puck.board.getBottomY() - 2 * GameEngine.puck.getRadius() + 4
						&& this.posX + 2 * this.radius >= GameEngine.puck.board.getRightX() - 2 * GameEngine.puck.getRadius() + 4) {
					GameEngine.puck.setVelocityX(-1);
					GameEngine.puck.setVelocityY(-1);
					this.posX -= 0.5f;
					this.posY -= 0.5f;
				} else {
					this.posX += this.velocityX;
					this.posY += this.velocityY;
				}
			} else {
				this.posX += this.velocityX;
				this.posY += this.velocityY;
			}
		} else {
			this.posY += this.velocityY;
			this.posX += this.velocityX;
		}

		//->> THIS IS OUR OLD MOVE METHOD ->> HAD BUG IN CORNERS OVERLAPPING PUCK
        //Moving X and Y with the velocity without any checks
		//this.posY += this.velocityY;
		//this.posX += this.velocityX;
	}

	private void addOpposition() {
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
		this.slideLevelX = 0.5f + Math.abs(this.velocityX * 0.03f);
		this.slideLevelY = 0.5f + Math.abs(this.velocityY * 0.03f);
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
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, GameEngine.color1.getColor()), Math.round(this.posX), Math.round(this.posY), null);
    }
    public void renderRight(Graphics g) {
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, GameEngine.color2.getColor()), Math.round(this.posX), Math.round(this.posY), null);
    }

}
