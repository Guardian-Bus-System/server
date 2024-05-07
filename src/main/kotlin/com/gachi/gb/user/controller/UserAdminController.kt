package com.gachi.gb.user.controller

import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminUpdateDto
import com.gachi.gb.user.service.UserAdminService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/users")
class UserAdminController (
  private val userAdminService: UserAdminService
) {
  @GetMapping("/")
  fun getUsers(): CommonResponse<List<User>> {
    return ok(userAdminService.getUsers())
  }

  @PutMapping("/user")
  fun updateUser(@RequestBody dto: UserAdminUpdateDto): CommonResponse<String> {
    return ok(userAdminService.updateUser(dto))
  }
}