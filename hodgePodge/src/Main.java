/**
 * Created by hp64 on 4/17/2016.
 */
public class Main {

    TwoDShape2 shapes[] = new TwoDShape2[4];

    shapes[0] = new Triangle("right", 8.0, 12.0);

    for (int i = 0; i < shapes.length; i++ ) {
        System.out.println('hey');
    }

}



abstract class TwoDShape2 {
    private double width, height;
    private String name;

    // A default constructor.
    TwoDShape() {
        width = height = 0.0;
        name = "null";
    }

    // Parameterized constructor.
    TwoDShape(double w, double h, String n) {
        width = w;
        height = h;
        name = n;
    }

    // Construct object with equal width and height.
    TwoDShape(double x, String n) {
        width = height = x;
        name = n;
    }

    // Construct an object from an object.
    TwoDShape(TwoDShape ob) {
        width = ob.width;
        height = ob.height;
        name = ob.name;
    }

    // Accessor methods for width and height.
    double getWidth() { return width; }
    double getHeight() { return height; }
    void setWidth(double w) { width = w; }
    void setHeight(double h) { height = h; }
    String getName() { return name; }

    void showDim() {
        System.out.println("Width and height are " +
                width + " and " + height);
    }

    // Now, area() is abstract.
    abstract double area();
}

// A subclass of TwoDShape for triangles.
class Triangle extends TwoDShape {
    private String style;
    // A default constructor.
    Triangle() {
        super();
        style = "null";
    }
    // Constructor for Triangle.
    Triangle(String s, double w, double h) {
        super(w, h, "triangle");
        style = s;
    }
    // Construct an isosceles triangle.
    Triangle(double x) {
        super(x, "triangle"); // call superclass constructor
        style = "isosceles";
    }
    // Construct an object from an object.
    Triangle(Triangle ob) {
        super(ob); // pass object to TwoDShape constructor
        style = ob.style;
    }

    double area() {
        return getWidth() * getHeight() / 2;
    }
    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}