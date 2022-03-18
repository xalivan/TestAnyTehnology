package com.example.testanytehnology.math;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

public class RandomGenerationInt {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int random = new Random().nextInt(10);
            integerList.add(random);
            System.out.println(random);
        }
        IntSummaryStatistics intSummaryStatistics = integerList.stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println(intSummaryStatistics);
    }
}
