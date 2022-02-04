package com.example.testanytehnology.paterns.composite;

class UnknownCar implements Car {
    public void draw(String color) {
        System.out.println("UnknownCar color: " + color);
    }
}
