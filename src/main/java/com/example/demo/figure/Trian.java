package com.example.demo.figure;

public class Trian extends Figure {
    private double z;

    public Trian(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
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
        z *= 1.25;
    }

    @Override
    public void min() {
        setX(getX() / 1.25);
        setY(getY() / 1.25);
        z /= 1.25;
    }

    @Override
    public double area() {
        double p = (getX() + getY() + z) / 2;
        double s = p * ((p - getX()) * (p - getY()) * (p - z));
        return Math.sqrt(s);
    }
}
