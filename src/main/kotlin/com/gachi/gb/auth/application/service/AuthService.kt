package com.gachi.gb.auth.application.service

import com.gachi.gb.auth.security.token.IdPasswordAuthentication
import com.gachi.gb.auth.security.token.RefreshAuthentication
import com.gachi.gb.auth.application.domain.JwtToken
import com.gachi.gb.auth.application.domain.RefreshToken
import com.gachi.gb.bus.domain.BusDetails
import com.gachi.gb.bus.repository.BusRepository
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

@Service
class AuthService (
  private val userRepository: UserRepository,
  private val roleRepository: RoleRepository,
  private val passwordEncoder: PasswordEncoder,
  private val authenticationManager: AuthenticationManager,
  private val createTokenService: CreateTokenService,
  private val busRepository: BusRepository
) {
  fun join(dto: UserJoinDto): JwtToken {
    if(userRepository.existsByLoginId(dto.loginId)) {
      throw IllegalArgumentException("중복되는 아이디입니다.")
    }

    val role = roleRepository.findById("USER").orElseGet {
      roleRepository.save(Role("USER", "사용자"))
    }

    val bus = busRepository.findByBusNumber(dto.boardingBus).orElseThrow {
      IllegalArgumentException("해당되는 버스를 찾을 수 없습니다.")
    }

    //상세 호차 정보 반환
    bus.lines = bus.lines.filter {
      it.detailsLine == dto.detailsLine
    } as MutableList<BusDetails>

    //변경 버스 여부 -> 이떄 없으면 기존의 경로 반환
    val changeBus = busRepository.findByBusNumber(dto.changeBoardingBus).orElse(bus)

    changeBus.lines = changeBus.lines.filter {
      it.detailsLine == dto.detailsLine
    } as MutableList<BusDetails>


    val user = User(
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
      mutableListOf(role)
    )

    userRepository.save(user)

    //회원 가입시 자동 로그인
    val authentication = authenticationManager.authenticate(
      IdPasswordAuthentication(dto.loginId, dto.pw)
    )

    return createToken(authentication)
  }

  fun login(dto: UserLoginDto): JwtToken {
    val authentication = authenticationManager.authenticate(
      IdPasswordAuthentication(dto.loginId, dto.pw)
    )
    return createToken(authentication)
  }

  //TODO: RefreshToken 재발급
  fun refresh(dto: RefreshToken): JwtToken {
    val authentication = authenticationManager.authenticate(
      RefreshAuthentication(null, dto.refreshToken)
    )
    val token = createToken(authentication)

    //Access Token의 유효기간을 체크하고 RefreshToken을 통해 assccToken을 재발급 해야됨
    return JwtToken(
      "",
      ""
    )
  }

  fun createToken(authentication: Authentication): JwtToken {
    return createTokenService.createToken(authentication)
  }

}