package com.gachi.gb.user.repository

import com.gachi.gb.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
  fun findByLoginId(id: String): User
}