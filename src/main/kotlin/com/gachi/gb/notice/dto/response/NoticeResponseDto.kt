package com.gachi.gb.notice.dto.response

import com.gachi.gb.user.domain.User
import java.time.LocalDateTime
import java.util.UUID

class NoticeResponseDto (
  var id: Int?,
  var content: String,
  var createAt: LocalDateTime,
  var updateAt: LocalDateTime?
) {
}