package com.gachi.gb.notice.service.impl

import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import com.gachi.gb.notice.repository.NoticeRepository
import com.gachi.gb.notice.service.NoticeService
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoticeServiceImpl(
  private val noticeRepository: NoticeRepository
): NoticeService {
  override fun getNotice(id: UUID): NoticeResponseDto {
    val notice = noticeRepository.findById(id).orElseThrow {
      IllegalArgumentException("공지사항이 존재하지 않습니다.")
    }
    return NoticeResponseDto(
      notice.id,
      notice.title,
      notice.content,
      notice.tag,
      notice.createAt,
      notice.updateAt,
      notice.uploadUser
    )
  }

  override fun getNotices(): List<NoticeListResponseDto> {
    return noticeRepository.findAll().map {
      NoticeListResponseDto(
        it.id,
        it.title,
        it.tag,
        it.createAt,
        it.updateAt
      )
    }
  }
}