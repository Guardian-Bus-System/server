package com.gachi.gb.user.dto

import com.gachi.gb.user.domain.Roles
import java.util.UUID


class UserAdminUpdateDto (
  var id: UUID,
  var loginId: String,
  var pw: String,
  var name: String,
  var phoneNumber: String,

  //학급
  var gradeClass: String,
  var number: Int,

  //탑승 여부
  var usingCk: Boolean,

  var boardingCk: Boolean? = false,

) {
}