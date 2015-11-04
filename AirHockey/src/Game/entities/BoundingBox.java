package Game.entities;

/**
 * Created by Siderov on 4.11.2015 г..
 */
public class BoundingBox {


    private static Integer x;
    private static Integer y;
    private static Integer radius;

    public BoundingBox(Integer x, Integer y, Integer radius) {
        this.x=x;
        this.y=y;
        this.radius=radius;
    }

    public static Integer getX() {
        return x;
    }
    public static void setX(Integer x) {
        BoundingBox.x = x;
    }

    public static Integer getY() {
        return y;
    }
    public static void setY(Integer y) {
        BoundingBox.y = y;
    }

    public static Integer getRadius() {
        return radius;
    }
}
