package com.gachi.gb.notice.service.impl

import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.dto.NoticeAddAdminDto
import com.gachi.gb.notice.dto.NoticeUpdateAdminDto
import com.gachi.gb.notice.dto.response.NoticeListAdminResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import com.gachi.gb.notice.repository.NoticeRepository
import com.gachi.gb.notice.service.NoticeAdminService
import com.gachi.gb.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NoticeAdminServiceImpl(
  private val noticeRepository: NoticeRepository
): NoticeAdminService {
  override fun getNotices(): List<NoticeListAdminResponseDto> {

    return noticeRepository.findAll().map {
      NoticeListAdminResponseDto(
        it.id,
        it.content,
        it.createAt,
        it.updateAt,
      )
    }
  }

  override fun getNotice(id: Int): NoticeResponseDto {
    val notice = noticeRepository.findById(id).orElseThrow {
      IllegalArgumentException("해당 공지가 존재하지 않습니다.")
    }

    return NoticeResponseDto(
      notice.id,
      notice.content,
      notice.createAt,
      notice.updateAt,
    )
  }

  override fun addNotices(dto: NoticeAddAdminDto): String {
    val newNotice = Notice(
      null,
      dto.content,
      LocalDateTime.now(),
      null,
      null
    )

    noticeRepository.save(newNotice)

    return "공지의 추가가 완료되었습니다."
  }

  override fun updateNotice(noticeId: Int, dto: NoticeUpdateAdminDto): String {
    val notice = noticeRepository.findById(noticeId).orElseThrow {
      IllegalArgumentException("공지가 존재하지 않습니다.")
    }

    notice.content = dto.content
    notice.updateAt = LocalDateTime.now()

    noticeRepository.save(notice)

    return "공지의 업데이트가 완료 되었습니다."
  }

  override fun deleteNotice(noticeId: Int): String {
    noticeRepository.findById(noticeId).orElseThrow {
      IllegalArgumentException("삭제 하려는 공지가 존재하지 않습니다.")
    }

    noticeRepository.deleteById(noticeId)

    return "공지의 삭제가 완료 되었습니다."
  }
}