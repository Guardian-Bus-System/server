package com.gachi.gb.notice.service

import com.gachi.gb.notice.dto.NoticeAddAdminDto
import com.gachi.gb.notice.dto.NoticeUpdateAdminDto
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import java.util.UUID

interface NoticeAdminService {
  fun getNotice(id: UUID): NoticeResponseDto

  fun getNotices(): List<NoticeListResponseDto>

  fun addNotices(userId: String, dto: NoticeAddAdminDto): String

  fun updateNotice(userId: String, noticeId: UUID, dto: NoticeUpdateAdminDto): String

  fun deleteNotice(userId: String, noticeId: UUID): String

}