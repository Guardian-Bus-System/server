package com.gachi.gb.rule.controller

import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.rule.dto.response.RuleListResponseDto
import com.gachi.gb.rule.service.RuleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "사용자_규칙", description = "사용자용 공지 API")
@RestController
@RequestMapping("/rules")
class RuleController (
  private val ruleService: RuleService
) {
  @Operation(summary = "모든 규칙 조회", description = "모든 규칙 리스트 조회")
  @GetMapping
  fun getRoles(): CommonResponse<List<RuleListResponseDto>> {
    return ok(ruleService.getRules())
  }
}