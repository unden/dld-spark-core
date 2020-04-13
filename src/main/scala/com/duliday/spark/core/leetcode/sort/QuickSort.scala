package com.duliday.spark.core.leetcode.sort

object QuickSort {
  def main(args: Array[String]): Unit = {
    val a = Array[Int](1,3,2,5,4,7,8,6)
    val sorted = quickSort(a)
    sorted.foreach(println)
  }

  /**
   * 在数据量较大的情况下，时间复杂度下降为O(n2)，不稳定的排序算法
   * @param a
   * @return
   */
  def quickSort(a: Array[Int]): Array[Int] = {
    if (a.length < 2) a
    else quickSort(a.filter(_ < a.head)) ++ a.filter(_ == a.head) ++ quickSort(a.filter(_ > a.head))
  }
}
