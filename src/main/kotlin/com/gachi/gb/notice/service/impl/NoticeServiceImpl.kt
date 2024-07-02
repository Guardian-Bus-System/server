package com.gachi.gb.notice.service.impl

import com.gachi.gb.notice.domain.NoticeViews
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import com.gachi.gb.notice.repository.NoticeRepository
import com.gachi.gb.notice.repository.NoticeViewsRepository
import com.gachi.gb.notice.service.NoticeService
import com.gachi.gb.user.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class NoticeServiceImpl(
  private val noticeRepository: NoticeRepository,
  private val noticeViewsRepository: NoticeViewsRepository,
  private val userRepository: UserRepository
): NoticeService {
  override fun getNotice(id: Int, userId: String): NoticeResponseDto {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("존재하지 않는 사용자 입니다.")
    }
    val notice = noticeRepository.findById(id).orElseThrow {
      IllegalArgumentException("공지사항이 존재하지 않습니다.")
    }

    val noticeViewsCK = notice.views?.any {
      it.views == user.id
    }?: false

    if(!noticeViewsCK) {
      val newNoticeView = NoticeViews(
        null,
        notice,
        user.id
      )
      noticeViewsRepository.save(newNoticeView)
    }

    return NoticeResponseDto(
      notice.id,
      notice.content,
      notice.createAt,
      notice.updateAt
    )
  }

  override fun getNotices(userId: String, pageable: Pageable): Page<NoticeListResponseDto> {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("존재하지 않는 사용자 입니다.")
    }

    return noticeRepository.findAllByOrderByCreateAtDescUpdateAtDesc(pageable).map {notice ->
      val views: Boolean = notice.views?.any {
        it.views == user.id
      } ?: false

      NoticeListResponseDto(
        notice.id,
        notice.content,
        notice.createAt,
        notice.updateAt,
        views //본인 읽었는지 확인
      )
    }
  }
}