package com.example.testanytehnology.strim;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapExample3 {
    public static void main(String[] args) {

        List<String> numbers = Arrays.asList("1", "2", "A", "B", "C1D2E3");

        numbers.stream()
                .map(x -> new Scanner(x).findAll("\\D+")
                        .map(MatchResult::group)
                        .collect(Collectors.toList())
                )
                .forEach(System.out::print);
        System.out.println();

        String collect = numbers.stream()
                .map(x -> new Scanner(x).findAll("\\D+")
                        .map(MatchResult::group)
                        .collect(Collectors.toList())
                )                                        // List<List<String>>
                .flatMap(List::stream)                    // List<String>
                .collect(Collectors.joining(", ", "Start { ", " } finish"));
        Stream.of(collect).forEach(System.out::print);
        System.out.println();
    }
}
