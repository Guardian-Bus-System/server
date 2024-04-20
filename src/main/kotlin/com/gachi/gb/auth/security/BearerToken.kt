package com.gachi.gb.auth.security

import com.gachi.gb.common.exception.UnauthorizedException
import org.springframework.util.StringUtils
import java.lang.IllegalArgumentException

//BearerToken 유효성 검사 및 Bearer 형식 전환
class BearerToken(val accessToken: String) {
  override fun toString(): String {
    return BEARER_PREFIX + accessToken
  }

  companion object {
    const val BEARER_PREFIX = "Bearer"

    @JvmStatic
    fun of(bearerToken: String): BearerToken {
      if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
        return BearerToken(bearerToken.substring(BEARER_PREFIX.length))
      }

      throw UnauthorizedException("해당 토큰의 형식이 잘못되었습니다.")
    }
  }
}