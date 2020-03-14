package com.duliday.spark.core


import com.duliday.spark.core.util.{DateUtil, IdentityCardValidate}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object FileOperate {

  val BATCH_SIZE = 100

  def main(args: Array[String]): Unit = {
//    method3("/Users/zhangxinwei/Desktop/身份证.txt")
    method2("/Users/zhangxinwei/Desktop/ids.txt")
//    println(sum())
  }

  def sum() = {
    val source = Source.fromFile("/Users/zhangxinwei/Desktop/amount.txt", "UTF-8")
    source.getLines().map(BigDecimal(_)).reduce(_ + _)
  }

  def method1() = {
    val source = Source.fromFile("/Users/zhangxinwei/Desktop/datas.txt", "UTF-8")
    val lines = source.getLines().toSeq.map(line => {
      val name = line.split("\\t")(0)
      val fileKey = line.split("\\t")(1)
      "\"" + fileKey + "\"" + ":" + "\"" + name + ".pdf" + "\""
    })

    var count = 1;
    for (elem <- lines) {
      print(elem + ",")
      if (count % BATCH_SIZE == 0) {
        println()
      }
      count += 1
    }
  }

  def method2(path: String) = {
    val source = Source.fromFile(path, "UTF-8")
    val lines = source.getLines().toArray.map{x => s"'${x}'"}
    println(lines.mkString(","))
  }

  def method3(path: String) = {
    val source = Source.fromFile(path, "UTF-8")
    val formatError = new ArrayBuffer[String]()
    val firstGenerations = new ArrayBuffer[String]()
    val ageError = new ArrayBuffer[String]()

    val lines = source.getLines().toArray.map{x => {
      if (x.length == 15) {
        firstGenerations += x
      } else {
        val isIdentityCard = IdentityCardValidate.validate(x)
        if (!isIdentityCard) {
          formatError += x
        } else {
          println(x)
          val birthday = x.substring(6, 14)
          val age = DateUtil.computeUserAge(DateUtil.parse(birthday, "yyyyMMdd"))
          if (age < 18 || age > 60) {
            ageError += x
          }
        }
      }
    }}
    println(formatError.mkString(","))
    println(firstGenerations.mkString(","))
    println(ageError.mkString(","))
  }

}


