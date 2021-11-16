package com.example.demo.figure;

public abstract class Figure {
    private double x;
    private double y;

    protected Figure(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }

    public abstract void rotate();

    public abstract void move();

    public abstract void max();

    public abstract void min();

    public abstract double area();
}
