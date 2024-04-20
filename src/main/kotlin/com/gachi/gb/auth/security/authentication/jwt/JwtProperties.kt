package com.gachi.gb.auth.security.authentication.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.security.Key

@Component
@ConfigurationProperties("app")
class JwtProperties {
  val jwtExpirationMs: Int? = null
  val jwtRefreshExpirationMs: Int? = null
  val issuer: String? = null
  val secretKey: String? = null
}