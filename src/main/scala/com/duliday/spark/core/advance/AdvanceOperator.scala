package com.duliday.spark.core.advance

import org.apache.spark.{SparkConf, SparkContext}

object AdvanceOperator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("operator")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    // 累加器
    accumulator(sc)

    // 自定义累加器
    customAccumulator(sc)

    // 广播变量(调优手段)
    broadcastDemo(sc)
  }

  def accumulator(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4), 2)
    /*val sum = rdd1.reduce(_ + _)
    println(sum)*/
    val accumulator = sc.longAccumulator("sum Accumulator")
    rdd1.foreach(i => {
        accumulator.add(i)
      }
    )
    println(accumulator)
  }

  def customAccumulator(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(List("hello", "world", "scala", "java", "spark", "python", "hive", "hbase"), 2)
    val logAccumulator = new LogAccumulator
    sc.register(logAccumulator, "log Accumulator")
    rdd1.foreach(word => {
      logAccumulator.add(word)
    })
    println(logAccumulator.value)
  }

  def broadcastDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(List((1, "a"), (2, "b"), (3, "c")))

    val list = List((1, 1), (2, 2), (3, 3))
    val broadcast = sc.broadcast(list)

    val rdd2 = rdd1.map {
      case (key, value) => {
        var v2: Any = null
        for (elem <- broadcast.value) {
          if (key == elem._1) {
            v2 = elem._2
          }
        }
        (key, (value, v2))
      }
    }
    rdd2.foreach(println)
  }

}
