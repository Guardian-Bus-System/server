package com.gachi.gb.user.service.impl

import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.user.domain.Role
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminUpdateDto
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.service.UserAdminService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAdminServiceImpl(
  private val userRepository: UserRepository,
  private val busRepository: BusRepository,
  private val passwordEncoder: PasswordEncoder
): UserAdminService {
  override fun getUsers(): List<User> {
    return userRepository.findAll()
  }

  override fun updateUser(dto: UserAdminUpdateDto): String {
    var user = userRepository.findById(dto.id).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    val bus = busRepository.findByBusNumber(dto.boardingBus).orElseThrow {
      IllegalArgumentException("해당 버스가 존재하지 않습니다.")
    }

    val changeBus = busRepository.findByBusNumber(dto.boardingChangeBus).orElseThrow {
      IllegalArgumentException("해당 변경 버스가 존재하지 않습니다.")
    }

    val adminRole = Role("ADMIN", "관리자")

    user.roles.forEach {
      if (it.name != dto.role) {
        dto.roles.add(adminRole)
      }
    }

    user = User(
      null,
      dto.loginId,
      passwordEncoder.encode(dto.pw),
      dto.name,
      dto.call,
      dto.gradeClass,
      dto.number,
      dto.usingCk,
      bus,
      changeBus,
      null,
      dto.roles
    )

    userRepository.save(user)

    return "유저 업데이트 완료"
  }
}