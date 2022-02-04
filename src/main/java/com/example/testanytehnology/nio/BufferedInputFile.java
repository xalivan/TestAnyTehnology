package com.example.testanytehnology.nio;


import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;

public class BufferedInputFile {
    static String ROOT = "C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\nio\\text.txt";

    public static void main(String[] args) throws Exception {
        read(ROOT);
        write();
    }

    private static String read(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        StringBuffer sb = new StringBuffer();
        while ((str = br.readLine()) != null) {
            sb.append(str).append("\n");
        }
        br.close();
        return sb.toString();
    }

    private static void write() throws Exception {
        BufferedReader br = new BufferedReader(new StringReader(BufferedInputFile.read(ROOT)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(getName())));
        String s;
        while ((s = br.readLine()) != null) {
            out.println(s);
        }
        out.close();
    }

    private static String getName() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return "C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\nio\\" + generatedString + ".txt";
    }
}

