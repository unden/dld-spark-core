package com.duliday.spark.core.util

import java.util.regex.Pattern

object IdentityCardValidate {

  def validate(identityCard: String) = {
    if (identityCard == null || identityCard.isEmpty) {
      false
    } else {
      val regex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
      val pattern = Pattern.compile(regex)
      val matcher = pattern.matcher(identityCard)
      if (!matcher.matches()) {
        false
      } else {
        true
      }
    }

  }



}
