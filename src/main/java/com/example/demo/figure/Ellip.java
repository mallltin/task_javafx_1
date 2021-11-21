package com.example.demo.figure;

public class Ellip extends Figure {
    private double rx;
    private double ry;

    public Ellip(double cx, double cy, double rx, double ry) {
        super(cx, cy);
        this.rx = rx;
        this.ry = ry;
    }

    public double getRx() {
        return rx;
    }

    public double getRy() {
        return ry;
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
        rx *= 1.25;
        ry *= 1.25;
    }

    @Override
    public void min() {
        setX(getX() / 1.25);
        setY(getY() / 1.25);
        rx /= 1.25;
        ry /= 1.25;
    }

    @Override
    public double area() {
        return Math.PI * rx * ry;
    }
}
