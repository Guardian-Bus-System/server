package com.gachi.gb.rule.controller

import com.gachi.gb.common.annotation.AuthUserId
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.rule.dto.admin.RuleAddAdminDto
import com.gachi.gb.rule.dto.admin.RuleUpdateAdminDto
import com.gachi.gb.rule.dto.response.admin.RuleListAdminResponseDto
import com.gachi.gb.rule.service.RuleAdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자_규칙", description = "관리자용 공지 API")
@RestController
@RequestMapping("/rules")
class RuleAdminController (
  private val ruleAdminService: RuleAdminService
) {
  @Operation(summary = "모든 규칙 조회", description = "모든 규칙 리스트 조회")
  @GetMapping("/")
  fun getRules(): CommonResponse<List<RuleListAdminResponseDto>> {
    return ok(ruleAdminService.getRules())
  }

  @Operation(summary = "규칙 추가", description = "규칙 추가 API")
  @PostMapping("/rule/add")
  fun addRule(
    @RequestBody dto: RuleAddAdminDto,
    @AuthUserId userId: String
  ): CommonResponse<String> {
    return ok(ruleAdminService.addRule(userId, dto))
  }

  @Operation(summary = "규칙 수정", description = "규칙 수정 API")
  @PutMapping("/{rule}/update")
  fun updateRule(
    @PathVariable("rule") ruleId: Long,
    @RequestBody dto: RuleUpdateAdminDto,
    @AuthUserId userId: String
  ): CommonResponse<String> {
    return ok(ruleAdminService.updateRule(ruleId, dto, userId))
  }

  @Operation(summary = "규칙 수정", description = "규칙 수정 API")
  @DeleteMapping("/{rule}/delete")
  fun deleteRule(
    @PathVariable("rule") ruleId: Long,
    @AuthUserId userId: String
  ): CommonResponse<String> {
    return ok(ruleAdminService.deleteRule(ruleId, userId))
  }
}