package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        GraphikObject c1 = new Circle();
        c1.draw();

    }
}

abstract class GraphikObject {
    int x, y;

    public void moveTo(int newX, int newY) {
        x = newX; y = newY;
    }

    abstract void draw();

}

class Circle extends GraphikObject {

    public void draw() {
        System.out.print("drawing circle");
    }

}
