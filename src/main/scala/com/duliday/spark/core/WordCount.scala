package com.duliday.spark.core

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("wc")
    val sc = new SparkContext(conf)

    sc.textFile("data/visit.txt").
      flatMap(_.split(",")).
      map((_,1)).
      reduceByKey(_+_).
      foreach(println)
  }

}
