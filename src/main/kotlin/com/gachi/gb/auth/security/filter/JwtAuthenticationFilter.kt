package com.gachi.gb.auth.security.filter

import com.gachi.gb.auth.security.BearerToken
import com.gachi.gb.auth.security.token.JwtAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter (
  private val authenticationManager: AuthenticationManager,
): OncePerRequestFilter() {
  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    try {
      //TODO:헤더에서 Authorization 가져와 변수로 지정
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

//        val userId = authentication.principal.toString()
//        val uuid: String = UUID.randomUUID() as String
//        MDC -> 로그를 확인할때 맨 앞에 붙는 고유 값? 이라고 생각하자
//        MDC.put("MDC_id", uuid)
      }
    } catch (e: Exception) {
      SecurityContextHolder.clearContext()
    }
//    MDC.clear()

    filterChain.doFilter(request, response)
  }

  companion object {
    const val AUTHORIZATION = "Authorization"
  }
}