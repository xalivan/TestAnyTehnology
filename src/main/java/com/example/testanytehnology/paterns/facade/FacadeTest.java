package com.example.testanytehnology.paterns.facade;

public class FacadeTest {//ัะตัั
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.startCar();
        System.out.println();
        facade.stoptCar();
    }
}
