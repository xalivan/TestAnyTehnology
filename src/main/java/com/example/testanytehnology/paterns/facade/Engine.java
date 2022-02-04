package com.example.testanytehnology.paterns.facade;

class Engine implements Car {
    public void start() {
        System.out.println("Запустить двигатель");
    }
    public void stop() {
        System.out.println("Остановить двигатель");
    }
}
