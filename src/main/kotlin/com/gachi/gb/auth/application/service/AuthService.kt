package com.gachi.gb.auth.application.service

import com.gachi.gb.auth.security.authentication.IdPasswordAuthentication
import com.gachi.gb.auth.security.authentication.jwt.JwtToken
import com.gachi.gb.user.domain.ClassInfo
import com.gachi.gb.user.domain.Role
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.repository.RoleRepository
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.response.dto.UserJoinDto
import com.gachi.gb.user.response.dto.UserLoginDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService (
  private val userRepository: UserRepository,
  private val roleRepository: RoleRepository,
  private val passwordEncoder: PasswordEncoder,
  private val authenticationManager: AuthenticationManager,
  private val createTokenService: CreateTokenService
) {
  fun join(dto: UserJoinDto): JwtToken {
    if(userRepository.existsByLoginId(dto.loginId)) {
      throw IllegalArgumentException("중복되는 아이디입니다.")
    }

    val role = roleRepository.findById("USER").orElseGet {
      roleRepository.save(Role("USER", "사용자"))
    }

    val user = User(
      null,
      dto.loginId,
      passwordEncoder.encode(dto.pw),
      dto.name,
      ClassInfo(
        null,
        dto.grade,
        dto.classNumber,
        dto.number
      ),
      mutableListOf(role)
    )

    userRepository.save(user)

    //회원 가입시 자동 로그인
    val authentication = authenticationManager.authenticate(
      IdPasswordAuthentication(dto.loginId, dto.pw, )
    )

    return createToken(authentication)
  }

  fun login(id: UUID, dto: UserLoginDto): JwtToken {
    val authentication = authenticationManager.authenticate(
      IdPasswordAuthentication(dto.loginId, dto.pw)
    )

    return createToken(authentication)
  }

  fun createToken(authentication: Authentication): JwtToken {
//    val userId = authentication.principal as String
//    val authority = authentication.authorities

    return createTokenService.createToken(authentication)
    // 토큰 만료일에 대해서 체크를 해야되는데 우선 모르니 스킵.
  }

}