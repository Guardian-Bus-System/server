package com.gachi.gb.notice.repository

import com.gachi.gb.notice.domain.Notice
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface NoticeRepository: JpaRepository<Notice, Int> {
  fun findAllByOrderByCreateAtDescUpdateAtDesc(pageable: Pageable): Page<Notice>
}