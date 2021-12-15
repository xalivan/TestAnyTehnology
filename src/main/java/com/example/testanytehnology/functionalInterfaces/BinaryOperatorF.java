package com.example.testanytehnology.functionalInterfaces;
// TODO BinaryOperator<T> – виконує логічну операцію над двома об’єктами
//        типу T та повертає результат того ж типу. Визначає метод apply
import java.util.function.*;
public class BinaryOperatorF {
    public static void main(String[] args) {
        Double s = 18.0;
        Double f = 2.0;
        BinaryOperator<Double> ref = (m, n) -> m/n;
        BinaryOperator<Double> sum = (m, n) -> m+n;
        BinaryOperator<Double> rez = (m, n) -> m-n;
        BinaryOperator<Double> mno = (m, n) -> m*n;

        System.out.println(ref.apply(s, f));
        System.out.println(sum.apply(s, f));
        System.out.println(rez.apply(s, f));
        System.out.println(mno.apply(s, f));

    }
}
