package com.example.testanytehnology.strim.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestComparable {
    public static void main(String[] args) {
        User userAnn = new User(1, "Ann", 20, 20, "woman");
        User userBob = new User(2, "Bon", 15, 21, "man");
        User userSam = new User(3, "Sam", 18, 25, "man");
        List<User> users = new ArrayList<>();
        users.add(userAnn);
        users.add(userBob);
        users.add(userSam);
        List<User> collect = users.stream().sorted(User::compareTo).collect(Collectors.toList());

        for (User user : collect) {
            System.out.println(user.toString());
        }
        System.out.println("--||--");
        users.stream().sorted(Comparator.comparingInt(User::getWeight)).forEach(System.out::println);
    }
}
