package com.gachi.gb.rule.service

import com.gachi.gb.rule.dto.admin.RuleAddAdminDto
import com.gachi.gb.rule.dto.admin.RuleUpdateAdminDto
import com.gachi.gb.rule.dto.response.admin.RuleListAdminResponseDto

interface RuleAdminService {
  fun getRules(): List<RuleListAdminResponseDto>

  fun addRule(userId: String, dto: RuleAddAdminDto): String

  fun updateRule(ruleId: Long, dto: RuleUpdateAdminDto, userId: String): String

  fun deleteRule(ruleId: Long,  userId: String): String

}