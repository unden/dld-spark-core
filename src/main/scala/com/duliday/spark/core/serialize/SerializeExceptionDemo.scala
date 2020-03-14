package com.duliday.spark.core.serialize

import org.apache.spark.{SparkConf, SparkContext}

object SerializeExceptionDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("serialize")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")


    val rdd = sc.parallelize(Array(5, 2, 3, 7))
    val searchUtil = new SearchUtil(4)
    val match1 = searchUtil.getMatch1(rdd)
    match1.collect().foreach(println)

  }

}
