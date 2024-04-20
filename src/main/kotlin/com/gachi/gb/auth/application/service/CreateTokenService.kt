package com.gachi.gb.auth.application.service

import com.gachi.gb.auth.security.JwtUtils
import com.gachi.gb.auth.security.authentication.jwt.JwtToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class CreateTokenService (
  private val jwtUtils: JwtUtils
) {
  fun createToken(authentication: Authentication): JwtToken {
    val accessToken = jwtUtils.generateAccessToken(authentication)
    val refreshToken = jwtUtils.generateRefreshToken(authentication)

    return JwtToken(accessToken, refreshToken)
  }
}