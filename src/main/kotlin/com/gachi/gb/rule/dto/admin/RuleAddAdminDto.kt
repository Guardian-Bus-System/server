package com.gachi.gb.rule.dto.admin

import com.gachi.gb.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.OneToOne
import java.time.LocalDateTime

class RuleAddAdminDto (
  var number: Long,
  var content: String,
) {
}