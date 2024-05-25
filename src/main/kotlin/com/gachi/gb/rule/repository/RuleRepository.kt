package com.gachi.gb.rule.repository

import com.gachi.gb.rule.domain.Rule
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RuleRepository: JpaRepository<Rule, Long> {
  fun findByNumber(number: Long): Optional<Rule>
}