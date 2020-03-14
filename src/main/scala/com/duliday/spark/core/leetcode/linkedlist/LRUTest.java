package com.duliday.spark.core.leetcode.linkedlist;

import java.util.Scanner;

public class LRUTest {

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            lru.put(value);

            System.out.println("放入成功");
        }
    }

}
