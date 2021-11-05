package com.example.testanytehnology.functionalInterfaces.consumer;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerEx2 {

    public static void main(String[] args) {

        Consumer<Integer> printMultiplyBy100 = (val) -> {
            System.out.println(val * 100);
        };

        printMultiplyBy100.accept(1);
        printMultiplyBy100.accept(2);
        printMultiplyBy100.accept(3);

        IntConsumer printMultiplyBy10 = a -> {
            System.out.println(a * 10);
        };

        printMultiplyBy10.accept(1);
        printMultiplyBy10.accept(2);
        printMultiplyBy10.accept(3);
    }
}
