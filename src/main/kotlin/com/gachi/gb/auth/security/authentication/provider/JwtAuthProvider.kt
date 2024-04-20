package com.gachi.gb.auth.security.authentication.provider

import com.gachi.gb.auth.security.JwtUtils
import com.gachi.gb.auth.security.SimpleUserPrincipal
import com.gachi.gb.auth.security.authentication.JwtAuthentication
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import java.util.*

//TODO: AuthenticationFilter의 토큰 검수를 위해 사용
// => credentials사용한 토큰 유효성 검수
class JwtAuthProvider(
  private val jwtUtils: JwtUtils
): AuthenticationProvider {
  override fun authenticate(authentication: Authentication?): Authentication {
    val token = authentication?.credentials.toString()
    if(!jwtUtils.validationToken(token)) {
      throw BadCredentialsException("유효하지 않은 토큰입니다.")
    }
    //TODO: UserDetails에서 userClaims와 authorities 추출
    val user = SimpleUserPrincipal(UUID.fromString(jwtUtils.getSubject(token)), jwtUtils.getAuthorities(token))

    //TODO: principal, credentials, authrorities 반환
    return JwtAuthentication(user, token, user.authorities)

  }

  override fun supports(authentication: Class<*>?): Boolean {
    return authentication == JwtAuthentication::class.java
  }
}