package com.duliday.spark.core.leetcode.sort;

import org.apache.spark.rpc.netty.RpcOutboxMessage;
import org.junit.Test;

public class SimpleSort {

    /**
     * 冒泡/插入/选择     O(n2)       基于比较的排序         原地排序
     * 快排/归并         O(nlogn)    基于比较的排序
     * 桶/计数/基数      O(n)        不基于比较
     */

    /**
     * 冒泡排序：稳定的排序算法
     * @param a
     * @return
     */
    public void bubbleSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        for (int i = 0; i < a.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }

            if (!flag) {
                return;
            }
        }
    }

    /**
     * 插入排序：稳定的排序算法
     * @param a
     */
    public void insertionSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

    /**
     * 选择排序：不稳定的排序算法
     * @param a
     */
    public void selectionSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;

        }
    }

    /**
     * 归并排序：时间复杂度较稳定O(nlogn)，稳定的排序算法，空间复杂度O(n)
     * @param a
     */
    public void mergeSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }


    }


    @Test
    public void test() {
        int[] a = {2, 3, 1, 6, 4, 5};
        insertionSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }

}
