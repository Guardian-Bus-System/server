package com.gachi.gb.notice.service

import com.gachi.gb.notice.dto.NoticeAddAdminDto
import com.gachi.gb.notice.dto.NoticeUpdateAdminDto
import com.gachi.gb.notice.dto.response.NoticeListAdminResponseDto
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import java.util.UUID

interface NoticeAdminService {
  fun getNotice(id: Int): NoticeResponseDto

  fun getNotices(): List<NoticeListAdminResponseDto>

  fun addNotices(dto: NoticeAddAdminDto): String

  fun updateNotice(noticeId: Int, dto: NoticeUpdateAdminDto): String

  fun deleteNotice(noticeId: Int): String

}