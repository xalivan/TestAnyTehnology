package com.example.testanytehnology.thread.vol.test1;

class Test {
    public static void main(String[] args)   {

        B b = new B();

        Thread t1 = new Thread(b);
        Thread t2 = new Thread(b);

        t1.setName("Thread A");
        t2.setName("Thread B");

        t1.start();
        t2.start();
    }
}
