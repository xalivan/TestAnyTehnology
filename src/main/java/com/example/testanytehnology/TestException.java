package com.example.testanytehnology;

import java.io.IOException;

public class TestException {
//    public static void throwit(){
//        System.out.println("throwit");
//        throw new RuntimeException();
//    }
//
//    public static void main(String[] args) {
//        try{
//            System.out.println("hello");
//            throwit();
//        } catch (Exception e){
//            System.out.println("catch");
//        } finally {
//            System.out.println("finally");
//        }
//        System.out.println("after");
//    }

//    public static void main(String[] args) {
//        try{
//            badMethod();
//            System.out.println("A");
//        } catch (Exception e){
//            System.out.println("B");
//        } finally {
//            System.out.println("C");
//        }
//        System.out.println("D");
//    }
//
//    public static void badMethod(){
//        throw new RuntimeException();
//    }

//    public static void aMethod() throws Exception{
//        try{
//            throw new Exception();
//        } finally {
//            System.out.println("finally");
//        }
//    }
//    public static void main(String[] args) {
//        try{
//            aMethod();
//        } catch (Exception e) {
//            System.out.println("exception");
//        }
//        System.out.println("finished");
//    }

//    public static void main(String[] args) {
//        try{
//            return;
//        } finally {
//            System.out.println("Finally");
//        }
//    }

//    public static void main(String[] args) {
//        try{
//            int x = 0;
//            int y = 5/x;
//        } catch (Exception e) {
//            System.out.println("exception");
//        }
//        System.out.println("finished");
//    }

//    public static void main(String[] args) {
//        try{
//            badMethod();
//            System.out.println("A");
//        } catch (Exception e){
//            System.out.println("B");
//        } finally {
//            System.out.println("C");
//        }
//        System.out.println("D");
//    }
//
//    public static void badMethod(){
//        throw new Error();
//    }

    public static void start() throws IOException{
        throw new RuntimeException("Not able to Start");
    }
    public static void main(String args[]) {
        try {
            start();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
