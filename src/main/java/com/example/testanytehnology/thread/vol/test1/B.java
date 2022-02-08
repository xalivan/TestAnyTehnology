package com.example.testanytehnology.thread.vol.test1;

class B extends Thread {
    A a = new A();

    public void run() {
        a.sum(10);
    }
}
