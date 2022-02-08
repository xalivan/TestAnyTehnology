package com.example.testanytehnology;

import java.util.*;

public class TestSet {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        List<Integer>integerList=new ArrayList<>(set);
        System.out.println(integerList.get(2));
        set.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}
