package com.gachi.gb.user.service.impl

import com.gachi.gb.user.domain.Role
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminUpdateDto
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.service.UserAdminService
import org.springframework.stereotype.Service

@Service
class UserAdminServiceImpl(
  private val userRepository: UserRepository
): UserAdminService {
  override fun getUsers(): List<User> {
    return userRepository.findAll()
  }

  override fun updateUser(dto: UserAdminUpdateDto): String {
    var user = userRepository.findById(dto.id).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }
    
    val adminRole = Role("ADMIN", "관리자")

    user.roles.forEach {
      if (it.name != dto.role) {
        dto.roles.add(adminRole)
      }
    }

    user = User(
      dto.id,
      dto.loginId,
      dto.pw,
      dto.name,
      dto.grade,
      dto.classNumber,
      dto.number,
      dto.roles
    )

    userRepository.save(user)
    
    return "유저 업데이트 완료"
  }
}