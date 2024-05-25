package com.gachi.gb.notice.service

import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import java.util.UUID

interface NoticeService {
  fun getNotice(id: UUID): NoticeResponseDto

  fun getNotices(): List<NoticeListResponseDto>

}