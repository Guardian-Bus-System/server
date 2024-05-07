package com.gachi.gb.user.dto

import com.gachi.gb.user.domain.Role
import java.util.UUID


class UserAdminUpdateDto (
  var role: String,

  val id: UUID,
  val loginId: String,
  var pw: String,
  var name: String,
  var grade: Int,
  var classNumber: Int,
  var number: Int,
  var roles: MutableList<Role>,
) {
}