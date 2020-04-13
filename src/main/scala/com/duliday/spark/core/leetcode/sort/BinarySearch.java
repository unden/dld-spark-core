package com.duliday.spark.core.leetcode.sort;

public class BinarySearch {

    /**
     * @param arr
     * @param target
     * @return
     * 二分查找法
     */
    public int bSearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] arr = {1, 2, 4, 5, 6, 7, 9, 10};
        System.out.println(search.bSearch(arr, 100));
    }

}
