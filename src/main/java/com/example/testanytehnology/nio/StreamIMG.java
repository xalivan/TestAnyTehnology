package com.example.testanytehnology.nio;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StreamIMG {
    public static void main(String[] args) throws Exception {
        //Создаем поток-чтения-байт-из-файла
        FileInputStream inputStream = new FileInputStream("/home/volodymyr/IdeaProjects/TestAnyTehnology/src/main/java/com/example/testanytehnology/nio/git.png");
        // Создаем поток-записи-байт-в-файл
        FileOutputStream outputStream = new FileOutputStream(getName());

        byte[] buffer = new byte[1000];
        while (inputStream.available() > 0) //пока есть еще непрочитанные байты
        {
            // прочитать очередной блок байт в переменную buffer и реальное количество в count
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count); //записать блок(часть блока) во второй поток
        }

        inputStream.close(); //закрываем оба потока. Они больше не нужны.
        outputStream.close();
    }

    private static String getName() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return "/home/volodymyr/IdeaProjects/TestAnyTehnology/src/main/java/com/example/testanytehnology/nio/" + generatedString + ".png";
    }
}
