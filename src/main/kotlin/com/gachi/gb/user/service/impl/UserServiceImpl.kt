package com.gachi.gb.user.service.impl

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.service.UserService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl (
  private val userRepository: UserRepository
): UserService {

  override fun getUserByLoginId(userLoginId: String): User {
    val user = userRepository.findByLoginId(userLoginId).orElseThrow {
      throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    return user
  }

  override fun getUserById(userId: UUID): User {
    val user = userRepository.findById(userId).orElseThrow {
      throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    return user
  }


}