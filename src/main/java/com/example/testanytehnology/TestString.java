package com.example.testanytehnology;

import org.apache.commons.lang.StringUtils;

public class TestString {
    public static void main(String[] args) {
        String s1 = "abc";

        String s2 = new String("abc").intern();
        String s3 = new String("abc").intern();

        String s4 = new String("abc");
        String s5 = new String("abc");

        System.out.println("s2==s3 " + (s2 == s3));
        System.out.println("s4==s5 " + (s4 == s5));
        System.out.println("s1==s2 " + (s1 == s2));
        System.out.println("s1.equals(s3) " + s1.equals(s3));

        StringUtils.isBlank(" "); // true
        StringUtils.isEmpty(" "); // false
    }
}
