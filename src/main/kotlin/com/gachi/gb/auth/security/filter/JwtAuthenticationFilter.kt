package com.gachi.gb.auth.security.filter

import com.gachi.gb.auth.security.BearerToken
import com.gachi.gb.auth.security.authentication.JwtAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter (
  private val authenticationManager: AuthenticationManager,
): OncePerRequestFilter() {
  private val log = LoggerFactory.getLogger(javaClass)
  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    try {
      //TODO:헤더에서 Authorization을 가져와 변수로 지정
      val authorization = request.getHeader(AUTHORIZATION)
      if(authorization != null) {
        //TODO:authorization 을 이용 하여 BearerToken.of 으로 형식을 바꿔줌
        val bearerToken = BearerToken.of(authorization)

        //TODO:JwtAuthenticationProvider 에 jwt 인증 시도
        val authentication = authenticationManager.authenticate(
          JwtAuthentication(null, bearerToken.accessToken)
        )
        //TODO:인증 성공시 보안 컨텍스트에 사용자 인증
        SecurityContextHolder.getContext().authentication = authentication
      }
    } catch (e: Exception) {
      SecurityContextHolder.clearContext()
    }

    filterChain.doFilter(request, response)
  }

  companion object {
    const val AUTHORIZATION = "Authorization"
  }
}