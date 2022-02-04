package com.example.testanytehnology.strim.listmap;


import java.util.*;
import java.util.stream.Collectors;

public class TestListMap {
    public static void main(String[] args) {
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80));
        list.add(new Hosting(2, "linode.com", 90));
        list.add(new Hosting(3, "digitalocean.com", 50));
        list.add(new Hosting(4, "aws.amazon.com", 60));
        list.add(new Hosting(5, "mkyong.com", 40));

        Map<Integer, String> result1 = list.stream().collect(
                Collectors.toUnmodifiableMap(Hosting::getId, Hosting::getName));
        System.out.println("Result 1 : " + result1);

        Map<String, Long> result2 = list.stream().collect(
                Collectors.toUnmodifiableMap(Hosting::getName, Hosting::getWebsites));
        System.out.println("Result 2 : " + result2);

        Map<Integer, String> result3 = list.stream().collect(
                Collectors.toUnmodifiableMap(Hosting::getId, Hosting::getName));
        System.out.println("Result 3 : " + result3);

        Map result4 = list.stream()
                .sorted(Comparator.comparingLong(Hosting::getWebsites).reversed())
                .collect(
                        Collectors.toMap(
                                Hosting::getName, Hosting::getWebsites,// key = name, value = websites
                                (oldValue, newValue) -> newValue,      // if same key, take the old key
                                LinkedHashMap::new                     // returns a LinkedHashMap, keep order
                        ));
        System.out.println("Result 4 : " + result4);
    }
}
