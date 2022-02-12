package com.example.testanytehnology.paterns.singelton;
//TODO 1
//class Singleton {
//    private static Singleton instance = null;
//    private Singleton() {}
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
//    public void setUp() {
//        System.out.println("Singleton created");
//    }
//}
//TODO 2
//public final class Singleton {
//    private static volatile Singleton instance;
//    public String value;
//
//    private Singleton(String value) {
//        this.value = value;
//    }
//
//    public static Singleton getInstance(String value) {
//        Singleton result = instance;
//        if (result != null) {
//            return result;
//        }
//        synchronized (Singleton.class) {
//            if (instance == null) {
//                instance = new Singleton(value);
//            }
//            return instance;
//        }
//    }
//}
//TODO 3
public class Singleton {

    public static class SingletonHolder {
        public static final Singleton HOLDER_INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
