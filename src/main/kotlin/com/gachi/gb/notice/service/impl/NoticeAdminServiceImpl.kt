package com.gachi.gb.notice.service.impl

import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.dto.NoticeAddAdminDto
import com.gachi.gb.notice.dto.NoticeUpdateAdminDto
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import com.gachi.gb.notice.repository.NoticeRepository
import com.gachi.gb.notice.service.NoticeAdminService
import com.gachi.gb.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class NoticeAdminServiceImpl(
  private val noticeRepository: NoticeRepository,
  private val userRepository: UserRepository
): NoticeAdminService {
  override fun getNotice(id: UUID): NoticeResponseDto {
    val notice = noticeRepository.findById(id).orElseThrow {
      IllegalArgumentException("해당 공지가 존재하지 않습니다.")
    }

    return NoticeResponseDto(
      notice.id!!,
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
        it.id!!,
        it.title,
        it.tag,
        it.createAt,
        it.updateAt!!
      )
    }

  }

  override fun addNotices(userId: String, dto: NoticeAddAdminDto): String {

    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val newNotice = Notice(
      null,
      dto.title,
      dto.content,
      dto.tag,
      LocalDateTime.now(),
      null,
      user
    )

    noticeRepository.save(newNotice)

    return "공지의 추가가 완료되었습니다."
  }

  override fun updateNotice(userId: String, noticeId:UUID, dto: NoticeUpdateAdminDto): String {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val notice = noticeRepository.findById(noticeId).orElseThrow {
      IllegalArgumentException("공지가 존재하지 않습니다.")
    }

    notice.title = dto.title
    notice.content = dto.content
    notice.tag = dto.tag
    notice.updateAt = LocalDateTime.now()
    notice.uploadUser = user

    noticeRepository.save(notice)

    return "공지의 업데이트가 완료 되었습니다."
  }

  override fun deleteNotice(userId: String, noticeId: UUID): String {
    userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    noticeRepository.findById(noticeId).orElseThrow {
      IllegalArgumentException("삭제 하려는 공지가 존재하지 않습니다.")
    }

    noticeRepository.deleteById(noticeId)

    return "공지의 삭제가 완료 되었습니다."
  }
}