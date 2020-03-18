package com.duliday.spark.core.leetcode.array;

public class PartiallySortedMatrix {

    /****************************** <begin> ***********************************/
    /**
     * @param matrix
     * @param target
     * @return
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 解题思路：从矩阵的右上角开始查找，如果target比右上角的数字大；则向下找，如果target比右上角的数字小，则向左找
     * 剑指Offer：3
     */
    public int[] find(int[][] matrix, int target) {
        int[] result = {-1, -1};
        int row = 0;
        int column = matrix[0].length - 1;
        while (row <= matrix.length - 1 && column >= 0) {
            if (target == matrix[row][column]) {
                result[0] = row;
                result[1] = column;
                return result;
            } else if (target < matrix[row][column]) {
                column--;
            } else {
                row++;
            }
        }
        return result;
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * 顺时针打印矩阵
     * @param arr
     */
    public void printMatrixClockWisely(int[][] arr) {
        if (arr == null) {
            return;
        }

        int start = 0;
        int rows = arr.length;
        int columns = arr[0].length;

        while (start * 2 < rows && start * 2 < columns) {
            printMatrixCircle(arr, rows, columns, start);
            start++;
        }
    }

    private void printMatrixCircle(int[][] arr, int rows, int columns, int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;

        // 从左到右
        for (int i = start; i <= endX; i++) {
            int number = arr[start][i];
            System.out.println(number);
        }

        // 从上到下
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                int number = arr[i][endX];
                System.out.println(number);
            }
        }

        // 从右到左
        if (start < endY && start < endX) {
            for (int i = endX - 1; i >= start; i--) {
                int number = arr[endY][i];
                System.out.println(number);
            }
        }

        // 从上到下
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; i--) {
                int number = arr[i][start];
                System.out.println(number);
            }
        }
    }
    /****************************** <end> ***********************************/

    public static void main(String[] args) {
        PartiallySortedMatrix client = new PartiallySortedMatrix();
        /*int[][] matrix = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
        int[] index = client.find(matrix, 7);*/

        int[][] matrix = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
        /*int[] index = client.find(matrix, 5);
        for (int i : index) {
            System.out.println(i);
        }*/

        client.printMatrixClockWisely(matrix);
    }

}
