package com.example.testanytehnology.strim.sideeffect;

import java.util.stream.IntStream;

public class SideEffectWithPeek {
    public static void main (String[] args) {
        long count = IntStream.range(0, 5)
                .unordered()
                .parallel()
                .map(x -> x * 2)
                .peek(System.out::println)
                .count();

        System.out.println(count);
    }
}