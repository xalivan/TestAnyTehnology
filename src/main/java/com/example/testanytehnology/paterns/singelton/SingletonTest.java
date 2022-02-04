package com.example.testanytehnology.paterns.singelton;

public class SingletonTest {//тест
    public static void main(String[] args){
        Singleton singelton = Singleton.getInstance();
        singelton.setUp();
    }
}
