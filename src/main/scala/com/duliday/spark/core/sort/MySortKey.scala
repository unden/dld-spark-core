package com.duliday.spark.core.sort

case class MySortKey(first:Int, second: Int, third: Int) extends Ordered[MySortKey] {

  /**
   * 实现排序规则
   * @param that
   * @return
   * 第一个字段升序，第二个字段降序，第三个字段升序
   */
  override def compare(that: MySortKey): Int = {
    if ((this.first - that.first) != 0) {
      this.first - that.first
    } else if ((this.second - that.second) != 0) {
      -(this.second - that.second)
    } else {
      this.third - that.third
    }
  }
}
