package com.example.testanytehnology.paterns.prototype;

public class PrototypeTest {
    public static void main(String[] args) {
        ComplicatedObject prototype = new ComplicatedObject();
        ComplicatedObject cloneOne = prototype.copy();
        cloneOne.setType(ComplicatedObject.Type.ONE);
        System.out.println(cloneOne.toString());

        ComplicatedObject cloneTwo = prototype.copy();
        cloneTwo.setType(ComplicatedObject.Type.TWO);
        System.out.println(cloneTwo.toString());
    }
}
