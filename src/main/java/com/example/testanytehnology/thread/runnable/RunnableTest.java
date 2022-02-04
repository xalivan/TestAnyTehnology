package com.example.testanytehnology.thread.runnable;

public class RunnableTest implements Runnable {

    @Override
    public void run() {
        for (int counter = 1; counter <= 5; counter++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(counter);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new RunnableTest()).start();
        }
        // Сюди можна додати дії, що виконуються паралельно з методом run()
    }

}