package com.gachi.gb.user.repository

import com.gachi.gb.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserRepository: JpaRepository<User, Int> {

  fun findById(id: UUID): Optional<User>

  fun findByLoginId(loginId: String): Optional<User>

  fun existsByLoginId(loginId: String): Boolean
}