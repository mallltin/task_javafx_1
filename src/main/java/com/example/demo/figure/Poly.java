package com.example.demo.figure;

public class Poly extends Figure{
    public Poly(double x, double y) {
        super(x, y);
    }

    public Poly(Double[] array) {
        super(array);
    }

    @Override
    public void rotate() {

    }

    @Override
    public void move() {

    }

    @Override
    public void max() {
        Double[] oldArray = getArray();
        Double[] newArray = new Double[oldArray.length];
        for(int i = 0; i < oldArray.length; i++){
            newArray[i] = oldArray[i] * 1.25;
        }
        setArray(newArray);
    }

    @Override
    public void min() {

    }

    @Override
    public double area() {
        return 0;
    }
}
