package com.practise.test;

import java.io.*;

/**
 * Created by Administrator on 05-09-2016.
 */
public class FileInput {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(new File("E:\\test.txt"));
        int c=0;
        while ((c=inputStream.read())!=-1) {
            System.out.println(c);
            System.out.println((char)c);
        }
    }
}
