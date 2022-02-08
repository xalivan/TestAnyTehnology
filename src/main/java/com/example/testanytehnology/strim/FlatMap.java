package com.example.testanytehnology.strim;

import com.example.testanytehnology.strim.model.Developer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class FlatMap {
    public static void main(String[] args) {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Stream.of(array)                        // Stream<String[]>
                .flatMap(Stream::of)            // Stream<String>
                .forEach(System.out::print);    // [a, b, c, d, e, f]
        System.out.println();

        Stream.of(array)                        // Stream<String[]>
                .flatMap(Stream::of)            // Stream<String>
                .filter(x -> !x.equals("a"))    // filter out the a
                .forEach(System.out::print);    // return a List
        System.out.println();


        Developer o1 = new Developer();
        o1.setName("mkyong");
        o1.addBook("Java 8 in Action");
        o1.addBook("Spring Boot in Action");
        o1.addBook("Effective Java (3nd Edition)");

        Developer o2 = new Developer();
        o2.setName("zilap");
        o2.addBook("Learning Python, 5th Edition");
        o2.addBook("Effective Java (3nd Edition)");

        List<Developer> developerList = new ArrayList<>();
        developerList.add(o1);
        developerList.add(o2);

        developerList.stream()
                .map(Developer::getBook)                            //  Stream<Set<String>>
                .flatMap(Collection::stream)                        //  Stream<String>
                .filter(x -> !x.toLowerCase().contains("python"))   //  filter python book
                .distinct().forEach(System.out::print);             //  remove duplicated

        developerList.stream()
                .map(Developer::getBook)
                .filter(x -> !x.contains("python"))
                .distinct().forEach(System.out::print);


    }
}
