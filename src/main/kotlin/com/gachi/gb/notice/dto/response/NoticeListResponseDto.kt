package com.gachi.gb.notice.dto.response

import com.gachi.gb.notice.domain.NoticeViews
import com.gachi.gb.user.domain.User
import java.time.LocalDateTime
import java.util.*

class NoticeListResponseDto (
  var id: Int?,
  var content: String,
  var createAt: LocalDateTime,
  var updateAt: LocalDateTime?,
  var views: Boolean?
) {
}