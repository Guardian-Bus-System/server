package com.gachi.gb.user.response.dto

import com.gachi.gb.user.domain.ClassInfo
import jakarta.persistence.Column

class UserJoinDto (
  var loginId: String,
  var pw: String,
  var name: String,

  var grade: Int,
  var classNumber: Int,
  var number: Int

) {
}