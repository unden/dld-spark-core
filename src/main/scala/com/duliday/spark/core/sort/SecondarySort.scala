package com.duliday.spark.core.sort

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object SecondarySort {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[1]")
      .setAppName("sort")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

//    sortBy(sc)
//    sortByKey(sc)
    topN(sc)
  }

  /**
   * 利用sortBy自然排序
   * @param sc
   */
  def sortBy(sc: SparkContext) = {
    val lines = sc.textFile("data/input/secondary-sort.txt")
    val tuple = lines.map(line => (line.split(",")(0).toInt, line.split(",")(1).toInt, line.split(",")(2).toInt))
    tuple.sortBy(x => x, false).foreach(println)
  }

  /**
   * 自定义排序Key
   * @param sc
   */
  def sortByKey(sc: SparkContext) = {
    val lines = sc.textFile("data/input/secondary-sort.txt")
    lines.map(line => (line.split(",")(0).toInt, line.split(",")(1).toInt, line.split(",")(2).toInt))
      .map(t => MySortKey(t._1, t._2, t._3)).map((_,1)).sortByKey().map(_._1).foreach(println)
  }

  /**
   * 分组取topN
   * @param sc
   */
  def topN(sc: SparkContext) = {
    val lines = sc.textFile("data/input/top-n.txt")
    lines.flatMap{line => {
        val scores = ArrayBuffer[(String, String, Double)]()
        val infos = line.split(",")
        val name = infos(0)
        val math = infos(1).split(":")(1)
        val chinese = infos(2).split(":")(1)
        val english = infos(3).split(":")(1)

        scores += new Tuple3(infos(1).split(":")(0), name, math.toDouble)
        scores += new Tuple3(infos(2).split(":")(0), name, chinese.toDouble)
        scores += new Tuple3(infos(3).split(":")(0), name, english.toDouble)
        scores
      }
    }.groupBy(_._1).map(info => (info._1, info._2.toList.sortWith(_._3 > _._3).take(5)))
      .flatMap(_._2)
      .foreach(println)
  }

}
