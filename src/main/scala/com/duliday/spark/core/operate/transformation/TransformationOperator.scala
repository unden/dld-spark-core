package com.duliday.spark.core.operate.transformation

import org.apache.spark.{SparkConf, SparkContext}

object TransformationOperator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("operator")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    // 从内存中创建RDD的两种方式
    //    makeRddFromMemory(sc)

    // 从外部文件中创建RDD
    //    makeRddFromFile(sc)

    // map
    //    mapDemo(sc)

    // mapPartitions:常用于数据结果写出的优化，如写出到mysql，可能出现OOM
    //    mapPartitionsDemo(sc)

    // mapPartitionsWithIndex,关注点在分区号
        mapPartitionsWithIndexDemo(sc)

    // flatMap:扁平化处理
    //    flatMapDemo(sc)

    // glom
    //    glomDemo(sc)

    // groupBy:分组(Shuffle)
    //    groupByDemo(sc)

    // filter:过滤
    //    filterByDemo(sc)

    // sample:采样
    //    sampleByDemo(sc)

    // distinct:去重(Shuffle)
    //    distinctByDemo(sc)

    // coalesce:减少分区数(实际上是合并分区，无Shuffle)
//        coalesceByDemo(sc)

    // repartition
    //    repartitionByDemo(sc)

    // sortBy
    //    sortByDemo(sc)

    // union:并集
    //    unionByDemo(sc)

    // subtract:补集
    //    subtractByDemo(sc)

    // intersection:交集
    //    intersectionByDemo(sc)

    // cartesian:笛卡尔积
    //    cartesianByDemo(sc)

    // zip:配对
    //    zipByDemo(sc)

    // partitionBy
    //    partitionByDemo(sc)

    // groupByKey
    //    groupByKeyDemo(sc)

    // reduceByKey:map端预聚合
    //    reduceByKeyDemo(sc)

    // aggregateByKey:次基础的
    //    aggregateByKeyDemo(sc)


    // foldByKey:最简单的
    //    foldByKeyDemo(sc)

    // combineByKey:最基础的
    //    combineByKeyDemo(sc)

    // sortByKey
    //    sortByKeyDemo(sc)

    // mapValue
    //    mapValuesDemo(sc)

    // join
//        joinDemo(sc)

    // leftOuterJoin
//    leftOuterJoinDemo(sc)

    // coGroup:返回的是value的集合
//    cogroupDemo(sc)

  }


  def makeRddFromMemory(sc: SparkContext): Unit = {
    // makeRDD底层调用的还是parallelize
    val rdd1 = sc.makeRDD(Array(1, 2, 3))
    rdd1.foreach(println)

    // 观察并行度
    //    rdd1.saveAsTextFile("data/output")

    val rdd2 = sc.parallelize(List("a", "c", "d"))
    rdd2.foreach(println)
  }

  def makeRddFromFile(sc: SparkContext) = {
    val rdd1 = sc.textFile("data/visit.txt")
    rdd1.foreach(println)

    // 观察并行度
    //    rdd1.saveAsTextFile("data/output")

  }

  def mapDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val rdd2 = rdd1.map(_ * 2)
    rdd2.foreach(println)
  }

  def mapPartitionsDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val rdd2 = rdd1.mapPartitions(_.map(_ * 2))
    rdd2.foreach(println)
  }

  def mapPartitionsWithIndexDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 10)
    val rdd2 = rdd1.mapPartitionsWithIndex {
      case (index, items) => {
        items.map((_, f"分区号:${index}"))
      }
    }
    rdd2.foreach(println)
  }

  def flatMapDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(Array(List(1, 2), List(3, 4)))
    val rdd2 = rdd1.flatMap(x => x)
    rdd2.foreach(println)
  }

  def glomDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16, 4)
    val rdd2 = rdd1.glom()
    //    rdd2.foreach(x => println(x.mkString(",")))
    rdd2.foreach(x => println(x.max))
  }

  def groupByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16)
    val rdd2 = rdd1.groupBy(_ + 1)
    rdd2.foreach(println)
  }

  def filterByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16)
    val rdd2 = rdd1.filter(_ > 10)
    rdd2.foreach(println)
  }

  def sampleByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16)
    val rdd2 = rdd1.sample(false, 0.2)
    rdd2.foreach(println)
  }

  def distinctByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(Array(1, 1, 2, 4, 5, 4, 7))
    //    val rdd2 = rdd1.distinct()
    // 去重后数据量变少，可以减少分区数
    val rdd2 = rdd1.distinct(2)
    rdd2.foreach(println)
    rdd2.saveAsTextFile("data/output")
  }

  def coalesceByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16, 4)
    println(s"初始化分区数:${rdd1.partitions.size}")
    // 数据过滤后，数据量变小，可以用coalesce减少分区数，但可能会发生数据倾斜，可用repartition代替
    val rdd2 = rdd1.filter(_ > 10).coalesce(3)
    println(s"coalesce后分区数:${rdd2.partitions.size}")
    rdd2.saveAsTextFile("data/output")
  }

  def repartitionByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16, 4)
    println(s"初始化分区数:${rdd1.partitions.size}")
    // 数据过滤后，数据量变小，可以用coalesce减少分区数，但可能会发生数据倾斜，可用repartition代替
    val rdd2 = rdd1.filter(_ > 10).repartition(2)
    println(s"repartition后分区数:${rdd2.partitions.size}")
    rdd2.saveAsTextFile("data/output")
  }

  def sortByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 16)
    //    val rdd2 = rdd1.sortBy(x => x)
    val rdd2 = rdd1.sortBy(x => x, false)
    rdd2.collect().foreach(println)
  }

  def unionByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 5)
    val rdd2 = sc.makeRDD(6 to 10)
    val rdd3 = rdd1.union(rdd2)
    rdd3.collect().foreach(println)
  }

  def subtractByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 8)
    val rdd2 = sc.makeRDD(6 to 10)
    val rdd3 = rdd1.subtract(rdd2)
    rdd3.collect().foreach(println)
  }

  def intersectionByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 8)
    val rdd2 = sc.makeRDD(6 to 10)
    val rdd3 = rdd1.intersection(rdd2)
    rdd3.collect().foreach(println)
  }

  def cartesianByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 8)
    val rdd2 = sc.makeRDD(6 to 10)
    val rdd3 = rdd1.cartesian(rdd2)
    rdd3.collect().foreach(println)
  }

  def zipByDemo(sc: SparkContext) = {
    val rdd1 = sc.makeRDD(1 to 3, 3)
    val rdd2 = sc.makeRDD(4 to 6, 3)
    val rdd3 = rdd1.zip(rdd2)
    rdd3.collect().foreach(println)
  }

  def partitionByDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(Array(("张三", 20), ("李四", 27), ("王五", 31), ("赵六", 16)), 4)
    println(rdd1.partitions.size)
    //    val rdd2 = rdd1.partitionBy(new HashPartitioner(2))
    // 自定义分区器
    val rdd2 = rdd1.partitionBy(new MyPartitioner(2))
    println(rdd2.partitions.length)
    rdd2.glom().mapPartitionsWithIndex {
      case (index, item) => {
        item.map(x => (index, x.mkString(",")))
      }
    }.collect().foreach(println)
    //    rdd2.glom().collect().foreach(x => println(x.mkString(",")))
  }

  def groupByKeyDemo(sc: SparkContext) = {
    val arr = Array("hello", "world", "scala", "hello", "spark")
    val rdd1 = sc.parallelize(arr).map((_, 1))
    val rdd2 = rdd1.groupByKey().map(x => (x._1, x._2.size))
    rdd2.collect().foreach(println)
  }

  def reduceByKeyDemo(sc: SparkContext) = {
    val arr = Array("hello", "world", "scala", "hello", "spark")
    val rdd1 = sc.parallelize(arr).map((_, 1))
    val rdd2 = rdd1.reduceByKey(_ + _)
    rdd2.collect().foreach(println)
  }

  def aggregateByKeyDemo(sc: SparkContext) = {
    val arr = Array(("a", 3), ("b", 5), ("a", 8), ("b", 4), ("b", 8), ("c", 6))
    val rdd1 = sc.parallelize(arr, 2)
    val rdd2 = rdd1.aggregateByKey(0)((x, y) => Math.max(x, y), _ + _)
    rdd2.collect().foreach(println)

    // 求平均数
    val arr1 = Array(1, 3, 5, 6)
    val rdd3 = sc.parallelize(arr1, 2)
    val rdd4 = rdd3.aggregate((0, 0))((x, y) => (x._1 + y, x._2 + 1), (x, y) => (x._1 + y._1, x._2 + y._2))
    println(rdd4._1 / rdd4._2)

    // 根据key求平均数
    val arr2 = Array(("a", 3), ("a", 5), ("a", 8), ("a", 4), ("b", 8), ("c", 6))
    val rdd5 = sc.parallelize(arr2, 2)
    val rdd6 = rdd5.aggregateByKey((0, 0))((x, y) => (x._1 + y, x._2 + 1), (x, y) => (x._1 + y._1, x._2 + y._2))
    rdd6.collect().foreach(println)
  }

  def foldByKeyDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    // 是aggregateByKey的简化
    val rdd2 = rdd1.foldByKey(0)(_ + _)
    rdd2.collect().foreach(println)
  }

  def combineByKeyDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(List(("a", 3), ("b", 2), ("a", 4), ("b", 3), ("c", 6), ("c", 8)), 3)
    // 是aggregateByKey的简化
    val rdd2 = rdd1.combineByKey((_, 1), (x: (Int, Int), y: Int) => (x._1 + y, x._2 + 1),
      (x: (Int, Int), y: (Int, Int)) => (x._1 + y._1, x._2 + y._2))
    rdd2.collect().foreach(x => println(x._1 + ":" + x._2._1 / x._2._2.toDouble))
  }

  def sortByKeyDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(Array((3, "aa"), (6, "cc"), (2, "bb"), (1, "dd")))
    val rdd2 = rdd1.sortByKey(false)
    rdd2.collect().foreach(println)
  }

  def mapValuesDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(Array((3, "aa"), (6, "cc"), (2, "bb"), (1, "dd")))
    // 根据key分组后用
    val rdd2 = rdd1.mapValues(x => s"hello:${x}")
    rdd2.collect().foreach(println)
  }

  def joinDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(Array((1, "a"), (2, "b"), (4, "c")))
    val rdd2 = sc.parallelize(Array((1, 4), (2, 5), (3, 6)))
    val rdd3 = rdd1.join(rdd2)
    rdd3.collect().foreach(println)
  }

  def leftOuterJoinDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(Array((1, "a"), (2, "b"), (4, "c")))
    val rdd2 = sc.parallelize(Array((1, 4), (2, 5), (3, 6)))
    val rdd3 = rdd1.leftOuterJoin(rdd2)
    rdd3.collect().foreach(println)
  }

  def cogroupDemo(sc: SparkContext) = {
    val rdd1 = sc.parallelize(Array((1, "a"), (2, "b"), (3, "c"), (4, "d")))
    val rdd2 = sc.parallelize(Array((1, 4), (2, 5), (3, 6), (5, 9)))
    val rdd3 = rdd1.cogroup(rdd2)
    rdd3.collect().foreach(println)
  }

}
