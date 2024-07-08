package com.gachi.gb.auth.security.provider

import com.gachi.gb.auth.security.SimpleUserPrincipal
import com.gachi.gb.auth.security.token.RefreshAuthentication
import com.gachi.gb.auth.security.JwtUtils
import com.gachi.gb.common.exception.UnauthorizedException
import com.gachi.gb.user.service.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class RefreshAuthProvider(
  private val userService: UserService,
  private val jwtUtils: JwtUtils
) : AuthenticationProvider {
  override fun authenticate(authentication: Authentication?): Authentication {
    val refreshToken = (authentication as RefreshAuthentication).credentials as String

    if (!jwtUtils.validationToken(refreshToken)) {
      throw UnauthorizedException("유효하지 않은 리프레시 토큰입니다.")
    }

    val userLoginId = jwtUtils.getSubject(refreshToken)
    val user = userService.getUserByLoginId(userLoginId)
    val authorities = jwtUtils.getAuthorities(refreshToken)

    return RefreshAuthentication(SimpleUserPrincipal(user.loginId, authorities), refreshToken, authorities)
  }

  override fun supports(authentication: Class<*>?): Boolean {
    return authentication == RefreshAuthentication::class.java
  }
}
