package com.gachi.gb.auth.security.token

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/*
UsernamePasswordAuthenticationToken은 AbstractAuthenticationToken를 상속하고 있다.

이때 AbstractAuthenticationToken은 Authentication을 상속하는 것을 알 수 있다.

즉 UsernamePasswordAuthenticationToken은 추후 인증이 끝나고
SecurityContextHolder.getContext()에 등록될 Authentication 객체를 반환해 주는 역할을 한다.

 */
class JwtAuthentication: UsernamePasswordAuthenticationToken {
  constructor(
    principal: Any?,
    credentials: Any?
  ) : super(principal, credentials)
  constructor(
    principal: Any?,
    credentials: Any?,
    authorities: Collection<GrantedAuthority>
  ) : super(
    principal,
    credentials,
    authorities
  )
}