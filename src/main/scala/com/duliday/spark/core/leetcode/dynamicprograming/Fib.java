package com.duliday.spark.core.leetcode.dynamicprograming;

/**
 * 斐波那契数列
 * 0,1,1,2,3,5,8,13,21
 */
public class Fib {

    public static void main(String[] args) {
        Fib fib = new Fib();
        System.out.println(System.currentTimeMillis());
        System.out.println(fib.fibWithRecursion(2));
        System.out.println(System.currentTimeMillis());
        System.out.println(fib.fibNoRecursion(2));
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 递归解法
     * @param n
     * @return
     */
    public long fibWithRecursion(long n) {
        if (n < 0) {
            return -1;
        }

        if (n <= 1) {
            return n;
        }

        return fibWithRecursion(n - 1) + fibWithRecursion(n - 2);
    }

    /**
     * 非递归解法
     * @param n
     * @return
     */
    public long fibNoRecursion(long n) {
        if (n < 0) {
            return -1;
        }

        if (n <= 1) {
            return n;
        }

        long preTwo = 0;
        long pre = 1;
        long result = preTwo + pre;
        for (long i = 2; i <= n; i++) {
            result = preTwo + pre;
            preTwo = pre;
            pre = result;
        }
        return result;
    }



}
