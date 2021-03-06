package com.example.testanytehnology.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InputOutputStream {
    public static void main(String[] args) throws Exception {
        //Создаем поток-чтения-байт-из-файла
        FileInputStream inputStream = new FileInputStream("/home/volodymyr/IdeaProjects/TestAnyTehnology/src/main/java/com/example/testanytehnology/nio/text.txt");
        // Создаем поток-записи-байт-в-файл
        FileOutputStream outputStream = new FileOutputStream("/home/volodymyr/IdeaProjects/TestAnyTehnology/src/main/java/com/example/testanytehnology/nio/textOut.txt");

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
}
