package com.duliday.spark.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FIleOp {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangxinwei/Desktop/result.txt");

        int a = 7;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String contentLine;
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while ((contentLine = bufferedReader.readLine()) != null) {

            String[] split = contentLine.split(",");
            String pic = split[0];
            if (pic.trim().length() == 0) {
                throw new RuntimeException();
            }
            pic = "\"" + pic + "\"";
            String name = split[1];
            if (name.trim().length() == 0) {
                throw new RuntimeException();
            }
            name = "\"" + name + ".pdf\"";
            stringBuilder.append(pic + ":" + name + ", ");
            i++;
            if (i % 60 == 0) {
                System.out.println(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
        System.out.println(stringBuilder.toString());
    }

}
