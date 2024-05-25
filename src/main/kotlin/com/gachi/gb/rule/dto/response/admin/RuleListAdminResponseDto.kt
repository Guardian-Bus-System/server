package com.gachi.gb.rule.dto.response.admin

import com.gachi.gb.user.domain.User
import java.time.LocalDateTime

class RuleListAdminResponseDto (
  var number: Long,

  var content: String,

  var createAt: LocalDateTime,

  var updateAt: LocalDateTime?,

  var user: User
) {
}