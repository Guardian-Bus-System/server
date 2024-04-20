package com.gachi.gb.auth.application.web

import com.gachi.gb.auth.application.service.AuthService
import com.gachi.gb.auth.security.authentication.jwt.JwtToken
import com.gachi.gb.common.annotation.AuthUserId
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.user.response.dto.UserJoinDto
import com.gachi.gb.user.response.dto.UserLoginDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/auth")
class AuthController (
  private val authService: AuthService
) {

  //관리자 전용 유저 회원가입 api
  @PostMapping("/join")
  fun join(
    @RequestBody dto: UserJoinDto
  ): CommonResponse<JwtToken> {
    return ok(authService.join(dto))
  }

  @PostMapping("/login")
  fun login(
    @AuthUserId id: UUID,
    @RequestBody dto: UserLoginDto
  ): CommonResponse<JwtToken> {
    return ok(authService.login(id, dto))
  }
}