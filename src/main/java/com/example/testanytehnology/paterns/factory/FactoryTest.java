package com.example.testanytehnology.paterns.factory;

public class FactoryTest {//ัะตัั
    public static void main(String[] args){
        String win = "linux";
        Factory factory = new Factory();
        OS os = factory.getCurrentOS(win);
        os.getOS();
    }
}
