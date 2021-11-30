package com.example.testanytehnology.math;

import java.util.Random;

public class RandomGenerationInt {
    public static void main(String[] args) {
        int random = new Random().nextInt(100000);
        System.out.println(random);
    }
}
