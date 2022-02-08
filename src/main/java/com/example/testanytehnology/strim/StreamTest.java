package com.example.testanytehnology.strim;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        //TODO 1) --- reduce ---
        List<Integer> collection = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(i + 10);
        }
        System.out.println(collection);
        System.out.println("--- reduce ---");
//        collection.stream().reduce((s1, s2) -> s1 + s2).orElse(0)
        Integer integer = collection.stream().reduce(Integer::sum).orElse(0);
        System.out.println("Integer::sum " + integer);
        Integer integer1 = collection.stream().reduce(Integer::max).orElse(100);
        System.out.println("Integer::max " + integer1);
        Integer integer2 = collection.stream().reduce(Integer::min).orElse(100);
        System.out.println("Integer::min " + integer2);
        Integer integer3 = collection.stream().filter(o -> o % 2 != 0).reduce(Integer::sum).orElse(0);
        System.out.println("Сумма нечетных чисел " + integer3);
        Integer integer4 = collection.stream().filter(o -> o % 2 == 0).reduce(Integer::sum).orElse(0);
        System.out.println("Сумма четных чисел " + integer4);

        List<String> stringList = List.of("1 1", "2 2", "3 3", "4 4");
        String[] strings = stringList.stream().flatMap((p) -> Arrays.stream(p.split(" "))).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));

        System.out.println();
        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c", "5", "x", "Y");
        list.stream().sorted().forEach(System.out::print);

        System.out.println();
        list.stream().map(String::toUpperCase).distinct().sorted().forEach(System.out::print);

        System.out.println();
        list.stream().map(String::toUpperCase).distinct().sorted(Comparator.reverseOrder()).forEach(System.out::print);

        System.out.println();
        collection.stream().filter(num -> num % 2 == 0).sorted(Comparator.reverseOrder()).forEach(System.out::print);

        System.out.println();
        list.stream().filter(x -> x.matches("\\d+")).distinct().sorted(Comparator.reverseOrder()).forEach(System.out::print);

        System.out.println();
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = Arrays.stream(numbers).reduce(0, Integer::sum);       // 55
        System.out.println("sum : " + sum);
        int sum3 = Arrays.stream(numbers).reduce(0, (a, b) -> a - b);   // -55
        System.out.println("sum : " + sum3);
        int sum4 = Arrays.stream(numbers).reduce(2, (a, b) -> a * b);   // 0, initial is 0, 0 * whatever = 0
        System.out.println("sum : " + sum4);
        int sum5 = Arrays.stream(numbers).reduce(100, (a, b) -> a / b);   // 0
        System.out.println("sum : " + sum5);
        System.out.println();

        List<String> listOfWords = Arrays.asList("acs", "sde", "cdk", "ams", "qe", "cdk");
        listOfWords.stream().sorted(Comparator.reverseOrder()).distinct().forEach(System.out::println);
        System.out.println();

    }
}
