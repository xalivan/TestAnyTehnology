package com.example.testanytehnology.functionalInterfaces.consumer;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerEx3 {

    public static void main(String[] args) {

        List<String> words = List.of("falcon", "wood", "rock", "forest",
                "river", "water");

        words.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
