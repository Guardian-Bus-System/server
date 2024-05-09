package com.gachi.gb.auth.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("app")
//data class => 복사 constructor을 생성해줘서 간편함
data class JwtProperties (
  var jwtExpirationMs: Int? = null,
  var jwtRefreshExpirationMs: Int? = null,
  var issuer: String? = null,
  var secretKey: String? = null
) {
}