package com.duliday.spark.core.cache

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object CacheOperator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("cache")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("data/checkpointDir")
    sc.setLogLevel("ERROR")

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))

    // 不会擦除lineage信息
    /*rdd1.cache()
    rdd1.persist()
    rdd1.persist(StorageLevel.MEMORY_ONLY)*/
    val rdd2 = rdd1.map((_,1))

    // 擦除lineage信息
    rdd1.checkpoint()
  }

}
