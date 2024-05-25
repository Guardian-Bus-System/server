package com.gachi.gb.rule.service

import com.gachi.gb.rule.dto.response.RuleListResponseDto

interface RuleService {
  fun getRules(): List<RuleListResponseDto>

}