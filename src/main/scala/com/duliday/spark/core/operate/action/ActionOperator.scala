package com.duliday.spark.core.operate.action

import org.apache.spark.{SparkConf, SparkContext}

object ActionOperator {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("action")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    // reduce
    //    reduceDemo(sc)

    // collect
    //    collectDemo(sc)

    // count
    //    countDemo(sc)

    // first
    //    firstDemo(sc)

    // take
    //    takeDemo(sc)

    // takeOrdered:排序后
    //    takeOrderedDemo(sc)

    // aggregate:该算子是action算子,分区间聚合，也会对zeroValue操作
    //    aggregateDemo(sc)

    // fold
    //    foldDemo(sc)

    // countByKey
//    countByKeyDemo(sc)

    // foreach
    foreachDemo(sc)
  }

  def reduceDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val result = rdd1.reduce(_ + _)
    println(result)
  }

  def collectDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val result = rdd1.collect()
    result.foreach(println)
  }

  def countDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val result = rdd1.count()
    println(result)
  }

  def firstDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val result = rdd1.first()
    println(result)
  }

  def takeDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(Array(2, 5, 4, 6, 8, 3))
    val result = rdd1.take(4).foreach(println)
  }

  def takeOrderedDemo(sc: SparkContext) = {
    val rdd = sc.parallelize(Array(2, 5, 4, 6, 8, 3))
    rdd.takeOrdered(3).foreach(println)
  }

  def aggregateDemo(sc: SparkContext) = {
    // 求平均数
    val arr1 = Array(1, 3, 5, 6)
    val rdd3 = sc.parallelize(arr1, 2)
    val rdd4 = rdd3.aggregate((0, 0))((x, y) => (x._1 + y, x._2 + 1), (x, y) => (x._1 + y._1, x._2 + y._2))
    println(rdd4._1 / rdd4._2.toDouble)
  }

  def foldDemo(sc: SparkContext) = {
    val rdd = sc.makeRDD(1 to 10, 2)
    val result = rdd.fold(0)(_ + _)
    println(result)
  }

  def countByKeyDemo(sc: SparkContext) = {
    val rdd = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    rdd.countByKey.foreach(println)
  }

  def foreachDemo(sc: SparkContext) = {
    val rdd = sc.makeRDD(1 to 5,2)
    rdd.foreach(println)
  }

}
