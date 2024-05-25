package com.gachi.gb.rule.service.impl

import com.gachi.gb.rule.domain.Rule
import com.gachi.gb.rule.dto.admin.RuleAddAdminDto
import com.gachi.gb.rule.dto.admin.RuleUpdateAdminDto
import com.gachi.gb.rule.dto.response.admin.RuleListAdminResponseDto
import com.gachi.gb.rule.repository.RuleRepository
import com.gachi.gb.rule.service.RuleAdminService
import com.gachi.gb.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RuleAdminServiceImpl(
  private val ruleRepository: RuleRepository,
  private val userRepository: UserRepository
): RuleAdminService {
  override fun getRules(): List<RuleListAdminResponseDto> {
    return ruleRepository.findAll().map {
      RuleListAdminResponseDto(
        it.number,
        it.content,
        it.createAt,
        it.updateAt,
        it.user
      )
    }
  }

  override fun addRule(userId: String, dto: RuleAddAdminDto): String {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    if(!ruleRepository.findByNumber(dto.number).isEmpty) {
      throw IllegalArgumentException("이미 같은 번호의 공지가 존재합니다.")
    }

    val rule = Rule(
      null,
      dto.number,
      dto.content,
      LocalDateTime.now(),
      null,
      user
    )

    ruleRepository.save(rule)

    return "공지 추가가 완료 되었습니다."
  }

  override fun updateRule(ruleId: Long, dto: RuleUpdateAdminDto, userId: String): String {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val rule = ruleRepository.findById(ruleId).orElseThrow {
      IllegalArgumentException("공지가 존재하지 않습니다.")
    }

    rule.number = dto.number
    rule.content = dto.content
    rule.updateAt = LocalDateTime.now()
    rule.user = user

    ruleRepository.save(rule)

    return "공지 수정이 완료 되었습니다."
  }

  override fun deleteRule(ruleId: Long, userId: String): String {
    userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val rule = ruleRepository.findById(ruleId).orElseThrow {
      IllegalArgumentException("공지가 존재하지 않습니다.")
    }

    ruleRepository.delete(rule)

    return "공지 삭제가 완료 되었습니다."
  }
}