package com.gachi.gb.notice.controller

import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.service.NoticeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/notices")
class NoticeController (
  private val noticeService: NoticeService
) {

  @GetMapping("/{notice}")
  fun getNotice(@PathVariable notice: UUID): CommonResponse<Notice> {
    return ok(noticeService.getNotice(notice))
  }

  @GetMapping("/")
  fun getNotices(): CommonResponse<List<Notice>> {
    return ok(noticeService.getNotices())
  }
}