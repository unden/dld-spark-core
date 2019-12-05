package com.duliday.spark.core.file

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object MySQLOperator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("mysql")
    // 设置并行度
    //      .set("spark.default.parallelism","3")
    val sc = new SparkContext(conf)
//    sc.setLogLevel("ERROR")

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/unden"
    val userName = "root"
    val passWd = "123456"

    val rdd = new JdbcRDD(sc, () => {
      Class.forName(driver)
      DriverManager.getConnection(url, userName, passWd)
    },
      "select * from `account` where `id` >= ? and id <= ?;",
      1,
      2,
      1,
      r => (r.getInt(1), r.getString(5))
    )

    //打印最后结果
    println(rdd.count())
    rdd.foreach(println)

    val rdd1 = sc.makeRDD(List(("zhangsan", 1), ("lisi", 2), ("wangwu", 3)))
    // 用foreachPartition替代foreach，但会出现OOM
    rdd1.foreachPartition(datas => {
      datas.foreach{
        case(name, age) => {
          println(s"$name:$age")
        }
      }
    })

    sc.stop()
  }

}
