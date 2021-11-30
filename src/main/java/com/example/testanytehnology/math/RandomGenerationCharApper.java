package com.example.testanytehnology.math;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class RandomGenerationCharApper {
    public static void main(String[] args) {
        String generatedString = RandomStringUtils.randomAlphabetic(30);

        System.out.println(generatedString);


//        int length = 30;
//        boolean useLetters = true;
//        boolean useNumbers = false;
//        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
//
//        System.out.println(generatedString);

//        int leftLimit = 48; // numeral '0'
//        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 10;
//        Random random = new Random();
//
//        String generatedString = random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//
//        System.out.println(generatedString);
    }
}
