package com.example.testanytehnology.functionalInterfaces;

import java.util.function.*;

// TODO Функціональний інтерфейс Consumer<T> призначений
//        для виконання деякої дії над об’єктом типу T.
//        Інтерфейс містить метод accept(), який отримує
//        об’єкт типу T та повертає результат типу void().
public class ConsumerF {
    public static void main(String[] args) {
        Consumer<Integer> cn;
        cn = (n) -> {
            Integer t = n, number = 0;
            while (t > 0) {
                number = number * 10 + t % 10;
                t = t / 10;
            }
            System.out.println("number = " + number);
        };
        cn.accept(10);
    }
}
