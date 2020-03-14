package com.duliday.spark.core.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

object DateUtil {

  def computeUserAge(birthday: Date) = {
    val calendar: Calendar = Calendar.getInstance
    calendar.setTime(new Date)
    val currentYear: Int = calendar.get(Calendar.YEAR)
    val currentMonth: Int = calendar.get(Calendar.MONTH)
    val currentDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.setTime(birthday)
    val userBirthdayYear: Int = calendar.get(Calendar.YEAR)
    val userBirthdayMonth: Int = calendar.get(Calendar.MONTH)
    val userBirthdayDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
    if (userBirthdayMonth > currentMonth) {
      currentYear - userBirthdayYear - 1
    } else if (userBirthdayMonth == currentMonth) {
      if (userBirthdayDay <= currentDay) {
        currentYear - userBirthdayYear
      } else {
        currentYear - userBirthdayYear - 1
      }
    } else{
      currentYear - userBirthdayYear
    }
  }

  def parse(str: String, format: String) = {
    val sdf = new SimpleDateFormat(format)
    sdf.parse(str)
  }


}
