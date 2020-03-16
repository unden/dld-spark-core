package com.duliday.spark.core.leetcode.bit;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
 * 剑指Offer 56-1
 */
public class NumbersAppearOnce {

    public static void findNumbersAppearOnce(int[] arr, int[] result) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int resultExclusiveOR = 0;
        for (int i = 0; i < arr.length; i++) {
            resultExclusiveOR ^= arr[i];
        }

        int indexOfOne = 0;
        while ( ((resultExclusiveOR & 1) == 0) && indexOfOne <= 32 ) {
            resultExclusiveOR = resultExclusiveOR >> 1;
            indexOfOne++;
        }

        int numberOne = 0;
        int numberTwo = 0;
        for (int i = 0; i < arr.length; i++) {
            if ( ((arr[i] >> indexOfOne) & 1) == 1 ) {
                numberOne = numberOne ^ arr[i];
            } else {
                numberTwo = numberTwo ^ arr[i];
            }
        }

        result[0] = numberOne;
        result[1] = numberTwo;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,4,3,2,1};
        int[] result = new int[2];

        findNumbersAppearOnce(arr, result);
        for (int i : result) {
            System.out.println(i);
        }
    }

}
