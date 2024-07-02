package com.gachi.gb.user.service.impl

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserUpdateDto
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.service.UserService
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl (
  private val userRepository: UserRepository,
  @Lazy private val passwordEncoder: PasswordEncoder
): UserService {

  override fun getUserByLoginId(userLoginId: String): User {
    val user = userRepository.findByLoginId(userLoginId).orElseThrow {
      throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    return user
  }

  override fun getUserById(userId: UUID): User {
    val checkUser = userRepository.findById(userId).orElseThrow {
      throw IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    return checkUser
  }

  override fun update(userLoginId: String, dto: UserUpdateDto): String {
    val user = userRepository.findByLoginId(userLoginId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    if(passwordEncoder.matches(dto.pw, user.pw)) {
      user.pw = passwordEncoder.encode(dto.changePw)
      user.gradeClass = dto.gradeClass
      user.number = dto.number
    } else {
      throw IllegalArgumentException("비밀번호가 같지 않습니다.")
    }

    userRepository.save(user)

    return "유저 업데이트가 완료되었습니다."
  }


}