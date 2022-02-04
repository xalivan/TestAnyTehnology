package com.example.testanytehnology.thread.thread;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        for (int counter = 1; counter <= 40; counter++) {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(counter);
        }
    }

    public static void main(String[] args) {
        new ThreadTest().start();
        // Сюди можна додати дії, що виконуються паралельно з методом run()
    }

}
