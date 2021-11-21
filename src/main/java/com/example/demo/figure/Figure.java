package com.example.demo.figure;

public abstract class Figure {
    private double x;
    private double y;
    private Double[] array;

    protected Figure(double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected Figure(Double[] array) {
        this.array = array;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Double[] getArray() {
        return array;
    }

    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }

    public void setArray(Double[] array) {
        this.array = array;
    }

    public abstract void rotate();

    public abstract void move();

    public abstract void max();

    public abstract void min();

    public abstract double area();
}
