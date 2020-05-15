package com.duliday.spark.core.leetcode.common;

public class CommonProblem {

    public static void main(String[] args) {
        CommonProblem commonProblem = new CommonProblem();
        System.out.println(commonProblem.add("111111111111111111111111111111111111111111111111111111111111", "999999999999999999999999999999999999999999999999999999999999999"));
    }

    /****************************** <begin> ***********************************/
    /**
     * 大数相加
     */
    public String add(String s1, String s2) {
        StringBuilder res = new StringBuilder("");
        int i = s1.length() - 1, j = s2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? s1.charAt(i) : 0;
            int n2 = j >= 0 ? s2.charAt(j) : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) res.append(1);
        return res.reverse().toString();
    }
    /****************************** <end> ***********************************/

}
