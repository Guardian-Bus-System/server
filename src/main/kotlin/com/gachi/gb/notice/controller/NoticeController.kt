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
  @Operation(summary = "모든 공지 조회", description = "모든 공지 리스트 조회")
  @GetMapping("/")
  fun getNotices(): CommonResponse<List<NoticeListResponseDto>> {
    return ok(noticeService.getNotices())
  }

  @GetMapping("/{notice}")
  fun getNotice(@PathVariable notice: UUID): CommonResponse<NoticeResponseDto> {
    return ok(noticeService.getNotice(notice))
  }

  @GetMapping("/")
  fun getNotices(): CommonResponse<List<Notice>> {
    return ok(noticeService.getNotices())
  }
}