package com.example.testanytehnology.functionalInterfaces;
import java.util.function.*;
// TODO Predicate<T> – визначає метод test().
//        Метод отримує параметром об’єкт
//        типу T і повертає логічне значення.
//        Метод test() визначає,
//        чи задовільняє об’єкт типу T деякій логічній умові.
public class PredicateF {
    public static void main(String[] args) {
        // 2. Одиночний лямбда-вираз, що визначає чи value додатнє
        Predicate<Integer> ref= (value) -> value>0;
        System.out.println("-37 > 0 = " + ref.test(-37));

        // 3. Блочний лямбда-вираз, що визначає чи value лежить в межах [0..99]
        Predicate<Integer> ref2 = (value) -> {
            if ((value>=0)&&(value<=99))
                return true;
            return false;
        };
        boolean result = ref2.test(77);
        System.out.println("value in the range [0..99] = " + result);
    }
}
