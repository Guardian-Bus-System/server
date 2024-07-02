package com.gachi.gb.notice.repository

import com.gachi.gb.notice.domain.NoticeViews
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface NoticeViewsRepository: JpaRepository<NoticeViews, Int> {
  fun existsByViews(userId: UUID): Boolean
}