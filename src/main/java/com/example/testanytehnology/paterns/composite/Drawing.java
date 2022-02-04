package com.example.testanytehnology.paterns.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Drawing implements Car {
    private List<Car> cars = new ArrayList<Car>();
    public void draw(String color) {
        for(Car car : cars) {
            car.draw(color);
        }
    }
    public void add(Car s){
        this.cars.add(s);
    }
    public void clear(){
        System.out.println();
        this.cars.clear();
    }
}
