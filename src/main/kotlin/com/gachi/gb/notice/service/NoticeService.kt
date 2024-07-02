package com.gachi.gb.notice.service

import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface NoticeService {
  fun getNotice(id: Int, userId: String): NoticeResponseDto

  fun getNotices(userId: String, pageable: Pageable): Page<NoticeListResponseDto>

}