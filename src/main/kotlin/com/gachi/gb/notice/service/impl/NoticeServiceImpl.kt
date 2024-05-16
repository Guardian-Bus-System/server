package com.gachi.gb.notice.service.impl

import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.repository.NoticeRepository
import com.gachi.gb.notice.service.NoticeService
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoticeServiceImpl(
  private val noticeRepository: NoticeRepository
): NoticeService {
  override fun getNotice(id: UUID): Notice {
    return noticeRepository.findById(id).orElseThrow {
      IllegalArgumentException("해당 공지에 대한 주소가 잘못되었습니다.")
    }
  }

  override fun getNotices(): MutableList<Notice> {
    return noticeRepository.findAll()
  }
}