package com.example.testanytehnology.thread.thread;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int r = ThreadLocalRandom.current() .nextInt(4, 77);
            System.out.println(r);
        }

    }
}
