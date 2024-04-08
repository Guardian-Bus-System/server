package com.gachi.gb.user.service.impl

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
  private val userRepository: UserRepository
): UserService {
  override fun getUser(userId: String): User {

    val user = userRepository.findByLoginId(userId)

    if(user.equals(null)) {
      throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    return user
  }
}