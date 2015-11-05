package Game.entities;

import Game.Game;

/**
 * Created by Siderov on 4.11.2015 г..
 */
//This class defines the board boundaries
public class BoundingBox {
    private Integer topY;
    private Integer bottomY;
    private Integer leftX;
    private Integer rightX;

    public BoundingBox(Integer boxStartingPointX, Integer boxStartingPointY, Integer boxWidth, Integer boxHeight) {
        this.topY = boxStartingPointY;
        this.bottomY =boxStartingPointY+boxHeight;
        this.leftX = boxStartingPointX;
        this.rightX = boxStartingPointX + boxWidth;
    }

    public Integer getTopY() {
        return topY;
    }
    public Integer getBottomY() {
        return bottomY;
    }
    public Integer getLeftX() {
        return leftX;
    }
    public Integer getRightX() {
        return rightX;
    }

    //this method now has an overload for when we access it with a puck rather than a mallet
    // this should be corrected => access it only with object from class circle with diff radius
//    public void keepInBoundaries(Mallet mallet){
//        //we define a circle by the most top left point of the smallest rectangle that can engulf the circle
//        //if mallet X exceeds maxX allowed then we put it at the right border
//        if(mallet.posX+2*mallet.radius>Game.board.getRightX()){
//            mallet.posX=Game.board.getRightX()-2*mallet.radius-1;
//        }
//        //if mallet X is less than allowed - put mallet at left border;
//        if(mallet.posX<Game.board.getLeftX()){
//            mallet.posX=Game.board.getLeftX()+1;
//        }
//        // if mallet y is less than allowed( is above board) put it at top border;
//        if(mallet.posY<Game.board.getTopY()){
//            mallet.posY=Game.board.getTopY()+1;
//        }
//        //if mallet y is more than allowed ( is below board) put it at bottom border;
//        if(mallet.posY+2*mallet.radius>Game.board.getBottomY()){
//            mallet.posY=Game.board.getBottomY()-2*mallet.radius -1;
//        }
//    }
}