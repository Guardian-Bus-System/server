package com.gachi.gb.user.controller

import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminAddDto
import com.gachi.gb.user.dto.UserAdminUpdateDto
import com.gachi.gb.user.service.UserAdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.UUID

@Tag(name = "관리자_사용자", description = "관리자용 사용자 관리 API")
@RestController
@RequestMapping("/admin/users")
class UserAdminController (
  private val userAdminService: UserAdminService
) {
  @Operation(summary = "사용자 목록 조회", description = "모든 유저 조회")
  @GetMapping
  fun getUsers(): CommonResponse<List<User>> {
    return ok(userAdminService.getUsers())
  }

  @Operation(summary = "사용자 추가", description = "유저 추가 API")
  @PostMapping("/user")
  fun addUser(
    @RequestBody dto: UserAdminAddDto): CommonResponse<String> {
    return ok(userAdminService.addUser(dto))
  }

  @Operation(summary = "사용자 수정", description = "유저 수정 API")
  @PutMapping("/user")
  fun updateUser(@RequestBody dto: UserAdminUpdateDto): CommonResponse<String> {
    return ok(userAdminService.updateUser(dto))
  }

  @Operation(summary = "사용자 삭제", description = "유저 삭제 API")
  @DeleteMapping("/{userId}")
  fun deleteUser(
    @PathVariable userId: UUID
  ): CommonResponse<String> {
    return ok(userAdminService.deleteUser(userId))
  }
}