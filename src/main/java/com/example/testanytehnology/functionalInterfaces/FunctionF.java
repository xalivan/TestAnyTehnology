package com.example.testanytehnology.functionalInterfaces;
import java.util.function.*;
//TODO Function<T, R> – визначає метод apply(),
//        який виконує операцію над об’єктом
//        типу T та повертає результат типу R;
public class FunctionF {
    public static void main(String[] args) {
        Function<Integer, Double> ref1 = n -> Math.sqrt(n);

        // 1.3. Продемонструвати обчислення кореня квадратного з 81
        Double result1 = ref1.apply(81);
        System.out.println("result1 = " + result1); // result1 = 9.0

        // 2.1. Оголосити посилання на функціональний інтерфейс Function<T, R>
        Function<Double, Boolean> ref2 = (n) -> {
            if (n<0)
                return true;
            return false;
        };

        // 2.3. Продемонструвати роботу лямбда-виразу
        Boolean result2 = ref2.apply(-20.5);
        System.out.println("result2 = " + result2);
    }
}
