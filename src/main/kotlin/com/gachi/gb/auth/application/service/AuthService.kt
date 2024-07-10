package com.gachi.gb.auth.application.service

import com.gachi.gb.auth.security.token.IdPasswordAuthentication
import com.gachi.gb.auth.security.token.RefreshAuthentication
import com.gachi.gb.auth.application.domain.JwtToken
import com.gachi.gb.auth.application.domain.RefreshToken
import com.gachi.gb.auth.security.MessageProperties
import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.user.domain.Roles
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.repository.RoleRepository
import com.gachi.gb.user.repository.UserRepository
import com.gachi.gb.user.response.dto.UserJoinDto
import com.gachi.gb.user.response.dto.UserLoginDto

import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.context.annotation.Lazy

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class AuthService (
  private val userRepository: UserRepository,
  private val roleRepository: RoleRepository,
  @Lazy private val passwordEncoder: PasswordEncoder,
  private val authenticationManager: AuthenticationManager,
  private val createTokenService: CreateTokenService,
  private val messageProperties: MessageProperties
) {
  private val messageService: DefaultMessageService = initialize(
    messageProperties.apiKey!!,
    messageProperties.apiSecret!!,
    "https://api.coolsms.co.kr"
  )

  private val verificationCodes = ConcurrentHashMap<String, String>()

  fun join(dto: UserJoinDto): JwtToken {
    if(userRepository.existsByLoginId(dto.loginId)) {
      throw IllegalArgumentException("중복되는 아이디입니다.")
    }

//    val role = roleRepository.findById("ADMIN").orElseGet {
//      roleRepository.save(Roles("ADMIN", "관리자"))
//    }

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
      false,
      null,
      mutableListOf(userRole)
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
    return createToken(authentication)
  }

  fun createToken(authentication: Authentication): JwtToken {
    return createTokenService.createToken(authentication)
  }

  fun sendVerificationCode(userId: String): String {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val userPhoneNumber = user.phoneNumber.replace("-", "")

    val verificationCode = UUID.randomUUID().toString().substring(0, 6)
    val message = Message(
      from = "01072286054",
      to = userPhoneNumber,
      text = "비밀번호 변경 인증 코드: $verificationCode"
    )
    messageService.sendOne(SingleMessageSendingRequest(message))
    verificationCodes[userPhoneNumber] = verificationCode
    return verificationCode
  }

  fun verifyCode(userId: String, code: String): Boolean {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val userPhoneNumber = user.phoneNumber.replace("-", "")

    val storedCode = verificationCodes[userPhoneNumber]

    return if (storedCode != null && storedCode == code) {
      verificationCodes.remove(userPhoneNumber)
      true
    } else {
      false
    }
  }

}