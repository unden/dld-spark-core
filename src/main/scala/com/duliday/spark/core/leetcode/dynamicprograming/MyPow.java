package com.duliday.spark.core.leetcode.dynamicprograming;

public class MyPow {

    /**
     * @param x
     * @param n
     * @return
     * 计算 x 的 n 次幂函数。
     */
    public double pow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        double ans = 1;
        for (int i = 0; i < n; i++) {
            ans = ans * x;
        }
        return ans;
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.pow(3, -2));
    }

}
