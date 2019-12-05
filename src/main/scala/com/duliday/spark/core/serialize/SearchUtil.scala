package com.duliday.spark.core.serialize

import org.apache.spark.rdd.RDD

class SearchUtil(m: Int) {

  //过滤出包含字符串的数据
  def isMatch(s: Int): Boolean = {
    s > m
  }

  //过滤出包含字符串的RDD
  def getMatch1(rdd: RDD[Int]): RDD[Int] = {
    rdd.filter(isMatch)
  }

  //过滤出包含字符串的RDD
  def getMatch2(rdd: RDD[Int]): RDD[Int] = {
    val s = m
    rdd.filter(_ > s)
  }

}
