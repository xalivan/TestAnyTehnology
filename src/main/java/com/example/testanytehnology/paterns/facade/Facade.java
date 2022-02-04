package com.example.testanytehnology.paterns.facade;

class Facade {
    private Key key;
    private Engine engine;
    public Facade() {
        key = new Key();
        engine = new Engine();
    }
    public void startCar() {
        key.start();
        engine.start();
    }
    public void stoptCar() {
        key.stop();
        engine.stop();
    }
}
