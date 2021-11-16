package com.example.demo.myFigureList;

public class MyFigureList {
    private String[] array = new String[4];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public String get(int index){
        checkIndex(index);
        return array[index];
    }

    public void add(String s){
        array[size] = s;
        size++;
        if(size >= array.length){
            String[] newArray = new String[array.length * 2];
            for(int i = 0; i < array.length; i++){
                newArray[i] = array[i];
            }
            array =newArray;
        }
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }
}
