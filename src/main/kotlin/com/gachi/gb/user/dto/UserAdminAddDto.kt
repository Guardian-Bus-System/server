package com.gachi.gb.user.dto

import java.util.*

class UserAdminAddDto (
  var loginId: String,
  var pw: String,
  var name: String,
  var phoneNumber: String,

  //학급
  var gradeClass: String,
  var number: Int,

  //탑승 여부
  var usingCk: Boolean,
) {
}