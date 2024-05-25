package com.gachi.gb.notice.dto.response

import java.time.LocalDateTime
import java.util.*

class NoticeListResponseDto (
  var id: UUID,
  var title: String,
  var tag: String,
  var createAt: LocalDateTime,
  var updateAt: LocalDateTime
) {
}