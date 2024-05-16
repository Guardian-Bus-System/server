package com.gachi.gb.notice.service

import com.gachi.gb.notice.domain.Notice
import java.util.UUID

interface NoticeService {
  fun getNotice(id: UUID): Notice

  fun getNotices(): MutableList<Notice>

}