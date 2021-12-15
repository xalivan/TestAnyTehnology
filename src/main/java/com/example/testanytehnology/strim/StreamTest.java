package com.example.testanytehnology.strim;

import com.example.testanytehnology.strim.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        //TODO 1) --- reduce ---
        List<Integer> collection=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(i+10);
        }
        System.out.println(collection);
        System.out.println("--- reduce ---");
//        collection.stream().reduce((s1, s2) -> s1 + s2).orElse(0)
        Integer integer = collection.stream().reduce(Integer::sum).orElse(0);
        System.out.println("Integer::sum "+integer);
        Integer integer1 = collection.stream().reduce(Integer::max).orElse(100);
        System.out.println("Integer::max "+integer1);
        Integer integer2 = collection.stream().reduce(Integer::min).orElse(100);
        System.out.println("Integer::min "+integer2);
        Integer integer3 = collection.stream().filter(o -> o % 2 != 0).reduce(Integer::sum).orElse(0);
        System.out.println("Сумма нечетных чисел "+integer3);
        Integer integer4 = collection.stream().filter(o -> o % 2 == 0).reduce(Integer::sum).orElse(0);
        System.out.println("Сумма четных чисел "+integer4);


        User userAnn=new User(1, "Ann", 10, 20, "woman");
        User userBob=new User(2, "Bon", 11, 21, "man");
        User userSam=new User(3, "Sam", 14, 25, "man");
        List<User>users=new ArrayList<>();
        users.add(userAnn);
        users.add(userBob);
        users.add(userSam);
        List<User> collect = users.stream().sorted(User::compareTo).collect(Collectors.toList());
        System.out.println("sorted () implements Comparable<User> to getAge()"+collect);


    }
}
