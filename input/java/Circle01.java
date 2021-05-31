import java.lang.Math;
import java.lang.IllegalArgumentException;

public class Circle01 {
    private final int radius;

    public Circle01(int radius) {
        if (radius <= 0) throw new IllegalArgumentException("Radius must be bigger than 0.");
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getDiameter() {
        return this.radius * 2;
    }

    public double getCircumference() {
        return this.radius * Math.PI * 2;
    }

    public double getArea() {
        return Math.pow(this.radius, 2) * Math.PI;
    }
}
