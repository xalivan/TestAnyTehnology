package com.example.testanytehnology.functionalInterfaces.Predicate;

import java.util.List;
import java.util.regex.Pattern;

public class AsMatchPredicate {

    public static void main(String[] args) {

        List<String> words = List.of("book", "bookshelf", "bookworm",
                "bookcase", "bookish", "bookkeeper", "booklet", "bookmark");

        var pred = Pattern.compile("book(worm|mark|keeper)?").asMatchPredicate();
        words.stream().filter(pred).forEach(System.out::println);
    }
}