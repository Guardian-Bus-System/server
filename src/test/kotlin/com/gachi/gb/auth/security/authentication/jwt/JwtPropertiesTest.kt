package com.gachi.gb.auth.security.authentication.jwt

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class JwtPropertiesTest (
) {

  @Test
  fun getJwtExpirationMs() {
    val testJwtProperties = JwtProperties()
    testJwtProperties.jwtExpirationMs = 3600000
    assertEquals(testJwtProperties.jwtExpirationMs, 3600000)
  }

  @Test
  fun getJwtRefreshExpirationMs() {
    val testJwtProperties = JwtProperties()
    testJwtProperties.jwtRefreshExpirationMs = 86400000
    assertEquals(testJwtProperties.jwtRefreshExpirationMs, 86400000)
  }

  @Test
  fun getIssuer() {
    val testJwtProperties = JwtProperties()
    testJwtProperties.issuer = "wxodi0@gmail.com"
    assertEquals(testJwtProperties.issuer, "wxodi0@gmail.com")
  }

  @Test
  fun getSecretKey() {
    val testJwtProperties = JwtProperties()
    testJwtProperties.secretKey = "내가 적어 놓을거라고 생각했냐?"
    assertEquals(testJwtProperties.secretKey, "내가 적어 놓을거라고 생각했냐?")
  }
}