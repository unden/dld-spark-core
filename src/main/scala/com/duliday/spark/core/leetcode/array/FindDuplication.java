package com.duliday.spark.core.leetcode.array;

public class FindDuplication {

    /**
     * @param arr
     * @return
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     * 请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，那么对应的输出是重复的数字2或者3。
     */
    public int getDuplicate(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return -1;
        }

        for (int i : arr) {
            if (i < 0 || i > arr.length - 1) {
                return -1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int temp;
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    return arr[i];
                }
                temp = arr[i];
                arr[i] = arr[temp];
                arr[temp] = temp;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FindDuplication findDuplication = new FindDuplication();
        int[] arr = {1, 2, 3, 3};
        System.out.println(findDuplication.getDuplicate(arr));
    }

}
