package com.gachi.gb.auth.security.authentication

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import java.security.Principal

class IdPasswordAuthentication: UsernamePasswordAuthenticationToken {
  constructor(
    principal: Any?
    , credentials: Any?
  ): super(principal, credentials)

  constructor(
    principal: Any?,
    credentials: Any?,
    authorities: Collection<GrantedAuthority>?
  ): super(
    principal, credentials, authorities
  )
}