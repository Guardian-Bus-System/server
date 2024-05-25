package com.gachi.gb.rule.service.impl

import com.gachi.gb.rule.dto.response.RuleListResponseDto
import com.gachi.gb.rule.repository.RuleRepository
import com.gachi.gb.rule.service.RuleService
import org.springframework.stereotype.Service

@Service
class RuleServiceImpl(
  private val ruleRepository: RuleRepository
): RuleService {
  override fun getRules(): List<RuleListResponseDto> {
    return ruleRepository.findAll().map {
      RuleListResponseDto(
        it.number,
        it.content
      )
    }
  }
}