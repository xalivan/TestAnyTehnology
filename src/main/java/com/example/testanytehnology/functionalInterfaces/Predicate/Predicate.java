package com.example.testanytehnology.functionalInterfaces.Predicate;

import java.util.List;

public class Predicate {
    public static void main(String[] args) {
//        Function<Long, Long> adder=x->x*2;
//        Long result = adder.apply((long) 4);
//        System.out.println("result = " + result);

        List<Integer> nums = List.of(2, 3, 1, 5, 6, 7, 8, 1, 9, 12);
        BiggerThanFive<Integer> btf = new BiggerThanFive<>();
        nums.stream().filter(btf).forEach(System.out::println);
    }
}

class BiggerThanFive<E> implements java.util.function.Predicate<Integer> {

    @Override
    public boolean test(Integer v) {
        Integer five = 5;
        return v > five;
    }
}
