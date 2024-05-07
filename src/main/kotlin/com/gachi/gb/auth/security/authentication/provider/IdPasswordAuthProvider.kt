package com.gachi.gb.auth.security.authentication.provider

import com.gachi.gb.auth.security.SimpleUserPrincipal
import com.gachi.gb.auth.security.authentication.IdPasswordAuthentication
import com.gachi.gb.common.exception.UnauthorizedException
import com.gachi.gb.user.service.UserService
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

//TODO: 간단한 로그인을 위해서 사용 => id, pw를 통한 login
@Component
class IdPasswordAuthProvider (
  private val userService: UserService,
  @Lazy private val passwordEncoder: PasswordEncoder
): AuthenticationProvider {
  override fun authenticate(authentication: Authentication?): Authentication {
    val userLoginId = (authentication as IdPasswordAuthentication).principal as String
    val user = userService.getUserByLoginId(userLoginId)
    val userPassword = authentication.credentials as String

    //matches = rawPassword, encodedPassword
    if(passwordEncoder.matches(userPassword, user.pw)) {
      return IdPasswordAuthentication(SimpleUserPrincipal(user.loginId, user.getAuthorities()), userPassword, user.getAuthorities())
    }
    throw UnauthorizedException("비밀번호가 맞지 않아 인증에 실패함.")
  }

  //supports == 지원?
  //만약 supports를 통해 token타입에 따라서 언제 Provider을 사용할지 지정
  //이때 false를 return 하게 되면 provider의 authentication메소드가 호출이 되지 않음
  override fun supports(authentication: Class<*>?): Boolean {
    return authentication == IdPasswordAuthentication::class.java
  }
}