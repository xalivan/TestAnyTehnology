package com.example.testanytehnology.nio;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StreamIMG {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\nio\\git.png");
        FileOutputStream outputStream = new FileOutputStream(getName());
        byte[] buffer = new byte[1000];
        while (inputStream.available() > 0) {
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count);
        }
        inputStream.close();
        outputStream.close();
    }

    private static String getName() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return "C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\nio\\" + generatedString + ".png";
    }
}
