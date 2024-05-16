package com.gachi.gb.user.dto

import com.gachi.gb.user.domain.Role
import java.util.UUID


class UserAdminUpdateDto (
  var id: UUID,
  var loginId: String,
  var pw: String,
  var name: String,
  var call: String,

  //학급
  var gradeClass: String,
  var number: Int,

  //탑승 여부
  var usingCk: Boolean,

  //기존 탑승 호차
  var boardingBus: Int,

  //변경 탑승 호차
  var boardingChangeBus: Int,

  //추가할 권한
  var role: String,

  var roles: MutableList<Role>,
) {
}