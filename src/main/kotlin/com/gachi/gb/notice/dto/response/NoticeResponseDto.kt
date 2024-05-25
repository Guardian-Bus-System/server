package com.gachi.gb.notice.dto.response

import com.gachi.gb.user.domain.User
import java.time.LocalDateTime
import java.util.UUID

class NoticeResponseDto (
  var id: UUID,
  var title: String,
  var content: String,
  var tag: String,
  var createAt: LocalDateTime,
  var updateAt: LocalDateTime?,

  var uploadUser: User
) {
}