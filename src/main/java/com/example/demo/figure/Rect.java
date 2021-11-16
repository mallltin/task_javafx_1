package com.example.demo.figure;

public class Rect extends Figure {

    public Rect(double x, double y) {
        super(x, y);
    }

    @Override
    public void rotate() {

    }

    @Override
    public void move() {

    }

    @Override
    public void max() {
        setX(getX() * 1.25);
        setY(getY() * 1.25);
    }

    @Override
    public void min() {
        setX(getX() / 1.25);
        setY(getY() / 1.25);
    }

    @Override
    public double area() {
        return getX() * getY();
    }
}
