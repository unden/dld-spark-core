package com.duliday.spark.core.operate.transformation

import org.apache.spark.Partitioner

class MyPartitioner(partitions: Int) extends Partitioner{

  override def numPartitions: Int = partitions

  override def getPartition(key: Any): Int = {
    1
  }
}
