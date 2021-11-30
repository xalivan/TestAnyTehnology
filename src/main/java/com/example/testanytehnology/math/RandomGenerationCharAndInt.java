package com.example.testanytehnology.math;

import org.apache.commons.lang.RandomStringUtils;

public class RandomGenerationCharAndInt {
    public static void main(String[] args) {
        String generatedString = RandomStringUtils.randomAlphanumeric(50);

        System.out.println(generatedString);
    }
}
