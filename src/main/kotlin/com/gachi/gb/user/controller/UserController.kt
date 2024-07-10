package com.gachi.gb.user.controller

import com.gachi.gb.auth.application.service.AuthService
import com.gachi.gb.common.annotation.AuthUserId
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.ChangePasswordDto
import com.gachi.gb.user.dto.UserUpdateDto
import com.gachi.gb.user.dto.UserVerifyCode
import com.gachi.gb.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "일반 사용자", description = "일반 사용자 API")
@RestController
@RequestMapping("/users")
class UserController (
  private val userService: UserService,
  private val authService: AuthService
) {
  @Operation(summary = "본인 프로필 조회", description = "토큰을 통한 본인 프로필 조회")
  @GetMapping("/me")
  fun getByUserId(@AuthUserId userId: String): CommonResponse<User> {
    return ok(userService.getUserByLoginId(userId))
  }

  @Operation(summary = "본인 정보 변경", description = "본인 프로필에 대한 정보를 수정 가능")
  @PutMapping("/me/update")
  fun updateUser(
    @AuthUserId userId: String,
    @RequestBody dto: UserUpdateDto
  ): CommonResponse<String> {
    return ok(userService.update(userId, dto))
  }

  //사용자 비밀번호 변경하기

  @Operation(summary = "비밀번호 변경용 인증 코드 발송")
  @PostMapping("/me/send-verification-code")
  fun sendVerificationCode(
    @AuthUserId userId: String
  ): CommonResponse<String> {
    return ok(authService.sendVerificationCode(userId))
  }

  @Operation(summary = "비밀번호 변경용 인증 코드 확인")
  @PostMapping("/me/verify-code")
  fun verifyCode(
    @AuthUserId userId: String,
    @RequestBody dto: UserVerifyCode
  ): CommonResponse<Boolean> {
    return ok(authService.verifyCode(userId, dto.code))
  }

  //사용안함
  @Operation(summary = "비밀번호 변경")
  @PutMapping("/me/change-password")
  fun changePassword(
    @AuthUserId userId: String,
    @RequestBody dto: ChangePasswordDto
  ): CommonResponse<String> {
    return ok(userService.changePassword(userId, dto))
  }
}