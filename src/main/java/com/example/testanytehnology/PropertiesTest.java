package com.example.testanytehnology;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args)
            throws Exception {

        // set up new properties object
        // from file "myProperties.txt"
        FileInputStream propFile = new FileInputStream("myProperties.txt");
        Properties p = new Properties(System.getProperties());
        p.load(propFile);

        // set the system properties
        System.setProperties(p);
        // display new properties
        System.getProperties().list(System.out);
    }
}