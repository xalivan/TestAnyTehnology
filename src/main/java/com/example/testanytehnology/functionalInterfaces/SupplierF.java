package com.example.testanytehnology.functionalInterfaces;
import java.util.function.*;
//TODO Функціональний інтерфейс Supplier<T> призначений для
//        отримання об’єкту типу T. В інтерфейсі
//        визначається метод get(), який повертає об’єкт типу T.
public class SupplierF {
    public static void main(String[] args) {
        // 1. Отримати об'єкт типу Integer
        // 1.1. Оголосити посилання типу Supplier<Integer>
        Supplier<Integer> ref;
        ref = () -> {
            return new Integer(23);
        };
             Integer objInt = ref.get();
        System.out.println("objInt = " + objInt);

        // 2. Отримати об'єкт типу Double[] - масив чисел
        // 2.1. Посилання + лямбда-вираз, який повертає масив з 5 випадкових чисел
        Supplier<Double[]> refArray = () -> {
            Double[] AD = new Double[5];
            for (int i=0; i<AD.length; i++)
                AD[i] = Math.random()*100;
            return AD; // повернути масив з 5 чисел
        };

        // 2.2. Викликати метод get() інтерфейсу
        Double[] AD2 = refArray.get();

        // 2.3. Вивести масив AD2
        for (Double x : AD2){
            System.out.print(x + " ");
        }
    }
}
