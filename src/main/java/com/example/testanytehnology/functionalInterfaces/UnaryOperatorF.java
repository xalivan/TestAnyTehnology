package com.example.testanytehnology.functionalInterfaces;
import java.util.function.*;
// TODO UnaryOperator<T> – виконує унарну операцію над
//        об’єктом узагальненого типу T та повертає
//        результат типу T. Визначає метод apply();
public class UnaryOperatorF {

    public static void main(String[] args) {
        UnaryOperator<Double> number = (n) -> n*2;
        Double t = number.apply(10.0);
        System.out.println("t = " + t);
    }

}
