package com.example.demo.figure;

public class Circ extends Figure {
    private double r;

    public Circ(double cx, double cy, double r) {
        super(cx, cy);
        this.r = r;
    }

    public double getR() {
        return r;
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
        r *= 1.25;
    }

    @Override
    public void min() {
        setX(getX() / 1.25);
        setY(getY() / 1.25);
        r /= 1.25;
    }

    @Override
    public double area() {
        return Math.PI * (r * r);
    }
}
