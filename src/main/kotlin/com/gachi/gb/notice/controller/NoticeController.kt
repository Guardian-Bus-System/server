package com.gachi.gb.notice.controller

import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import com.gachi.gb.notice.service.NoticeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "일반 공지", description = "일반 공지 API")
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

  @Operation(summary = "공지 상세 조회", description = "공지의 pk인 UUID를 path값을 통해서 조회")
  @GetMapping("/{notice}")
  fun getNotice(@PathVariable notice: UUID): CommonResponse<NoticeResponseDto> {
    return ok(noticeService.getNotice(notice))
  }
}