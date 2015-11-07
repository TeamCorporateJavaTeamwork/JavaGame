package Game.entities;

//This class defines boundaries for objects
public class BoundingBox {
    private Integer topY;
    private Integer bottomY;
    private Integer leftX;
    private Integer rightX;

    public BoundingBox(Integer boxStartingPointX, Integer boxStartingPointY, Integer boxWidth, Integer boxHeight) {
        this.topY = boxStartingPointY;
        this.bottomY = boxStartingPointY+boxHeight;
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
}