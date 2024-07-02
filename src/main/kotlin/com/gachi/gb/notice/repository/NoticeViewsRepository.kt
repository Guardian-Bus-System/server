package com.gachi.gb.notice.repository

import com.gachi.gb.notice.domain.NoticeViews
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeViewsRepository: JpaRepository<NoticeViews, Int> {
}