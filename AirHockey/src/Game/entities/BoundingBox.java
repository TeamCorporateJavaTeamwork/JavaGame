package Game.entities;

//This class defines boundaries for objects
public class BoundingBox {
    private int topY;
    private int bottomY;
    private int leftX;
    private int rightX;

    public BoundingBox(int boxStartingPointX, int boxStartingPointY, int boxWidth, int boxHeight) {
        this.topY = boxStartingPointY;
        this.bottomY = boxStartingPointY+boxHeight;
        this.leftX = boxStartingPointX;
        this.rightX = boxStartingPointX + boxWidth;
    }

    public int getTopY() {
        return topY;
    }
    public int getBottomY() {
        return bottomY;
    }
    public int getLeftX() {
        return leftX;
    }
    public int getRightX() {
        return rightX;
    }
}