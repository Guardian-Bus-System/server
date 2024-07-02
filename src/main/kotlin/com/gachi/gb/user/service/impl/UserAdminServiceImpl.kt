package com.gachi.gb.user.service.impl

import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.user.domain.Roles
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminAddDto
import com.gachi.gb.user.dto.UserAdminUpdateDto
import com.gachi.gb.user.repository.RoleRepository
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.service.UserAdminService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserAdminServiceImpl(
  private val userRepository: UserRepository,
  private val busRepository: BusRepository,
  private val roleRepository: RoleRepository,
  private val passwordEncoder: PasswordEncoder
): UserAdminService {
  override fun getUsers(): List<User> {
    return userRepository.findAll()
  }

  override fun addUser(dto: UserAdminAddDto): String {
    if(userRepository.existsByLoginId(dto.loginId)) {
      throw IllegalArgumentException("이미 존재하는 아이디입니다.")
    }

    val userRole = roleRepository.findById("USER").orElseGet {
      roleRepository.save(Roles("USER", "사용자"))
    }

    val user = User(
      null,
      dto.loginId,
      passwordEncoder.encode(dto.pw),
      dto.name,
      dto.phoneNumber,
      dto.gradeClass,
      dto.number,
      dto.usingCk,
      null,
      mutableListOf(userRole)
    )

    userRepository.save(user)
    
    return "유저 등록 완료"
  }

  override fun updateUser(dto: UserAdminUpdateDto): String {
    var user = userRepository.findById(dto.id).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    user = User(
      null,
      dto.loginId,
      passwordEncoder.encode(dto.pw),
      dto.name,
      dto.phoneNumber,
      dto.gradeClass,
      dto.number,
      dto.usingCk,
      dto.boardingCk,
      user.roles
    )

    userRepository.save(user)

    return "유저 업데이트 완료"
  }

  override fun deleteUser(userId: UUID): String {
    val user = userRepository.findById(userId).orElseThrow{
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    userRepository.delete(user)

    return "유저 삭제 완료"
  }
}