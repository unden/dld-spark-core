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

    /**
     * @param nums
     * @return
     * leetcode:287
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * 将数组类比为一个链表，问题转换为求链表中环的入口
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    public static void main(String[] args) {
        FindDuplication findDuplication = new FindDuplication();
        int[] arr = {1, 2, 3, 3};
        System.out.println(findDuplication.getDuplicate(arr));
        int[] arr2 = {1,1,1,1,1,1,1};
        findDuplication.findDuplicate(arr2);
    }


}
