package com.duliday.spark.core.syntax

import org.junit.{Assert, Test}


class UsingUnit {


  @Test
  def listAdd() = {
    val list = List(1, 2, 3)
    Assert.assertEquals(3, list.size)
  }

}
