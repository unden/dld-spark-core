package com.duliday.spark.core.advance

import java.util

import org.apache.spark.util.AccumulatorV2

class LogAccumulator extends AccumulatorV2[String, util.ArrayList[String]] {

  private val logList = new util.ArrayList[String]()

  override def isZero: Boolean = logList.isEmpty

  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = new LogAccumulator

  override def reset(): Unit = logList.clear()

  override def add(v: String): Unit = {
    if (v.contains("h")) logList.add(v)
  }

  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    other match {
      case o: LogAccumulator => logList.addAll(o.value)
    }
  }

  override def value: util.ArrayList[String] = logList
}
