package com.gachi.gb.notice.controller

import com.gachi.gb.common.annotation.AuthUserId
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.notice.domain.Notice
import com.gachi.gb.notice.dto.NoticeAddAdminDto
import com.gachi.gb.notice.dto.NoticeUpdateAdminDto
import com.gachi.gb.notice.dto.response.NoticeListResponseDto
import com.gachi.gb.notice.dto.response.NoticeResponseDto
import com.gachi.gb.notice.service.NoticeAdminService
import com.gachi.gb.notice.service.NoticeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/admin/notices")
class NoticeAdminController(
  private val noticeAdminService: NoticeAdminService
) {
  @Operation(summary = "모든 공지 조회", description = "모든 공지 리스트 조회")
  @GetMapping("/")
  fun getNotices(): CommonResponse<List<NoticeListResponseDto>> {
    return ok(noticeAdminService.getNotices())
  }

  @Operation(summary = "공지 상세 조회", description = "공지의 pk인 UUID를 path값을 통해서 조회")
  @GetMapping("/{noticeId}")
  fun getNotice(@PathVariable noticeId: UUID): CommonResponse<NoticeResponseDto> {
    return ok(noticeAdminService.getNotice(noticeId))
  }

  @Operation(summary = "공지 추가", description = "dto를 통해 공지 추가")
  @PostMapping("/notice")
  fun addNotice(
    @AuthUserId userId: String,
    dto: NoticeAddAdminDto
  ): CommonResponse<String> {
    return ok(noticeAdminService.addNotices(userId, dto))
  }

  @Operation(summary = "공지 수정", description = "dto를 통해 공지 수정")
  @PutMapping("/{notice}")
  fun updateNotice(
    @AuthUserId userId: String,
    @PathVariable("notice") noticeId: UUID,
    dto: NoticeUpdateAdminDto
  ): CommonResponse<String> {
    return ok(noticeAdminService.updateNotice(userId, noticeId, dto))
  }

  @Operation(summary = "공지 삭제", description = "공지의 pk인 UUID를 path값을 통해서 삭제")
  @DeleteMapping("/{notice}")
  fun deleteNotice(
    @AuthUserId userId: String,
    @PathVariable("notice") noticeId: UUID
  ): CommonResponse<String> {
    return ok(noticeAdminService.deleteNotice(userId, noticeId))
  }
}