package com.duliday.spark.core.util

object Demo1 {


  def main(args: Array[String]): Unit = {
    println(quickSort(List(5, 2, 3, 1, 4)))
    println(p1[Int, Int, Int](1, (x, y) => x + y))
  }

  def p[A, B, C](a: A, f: (A, B) => C) : B => C = {
    def result(b: B) : C = {
      f(a, b)
    }
    result
  }

  def p1[A, B, C](a: A, f: (A, B) => C) : B => C = {
    f(a, _ : B)
  }

  def quickSort(list: List[Int]) : List[Int] = {
    list match {
      case Nil => Nil
      case x :: xs => {
        val (smaller, larger) = xs.partition(_ < x)
        quickSort(smaller) ++ (x :: quickSort(larger))
      }
    }
  }

}
