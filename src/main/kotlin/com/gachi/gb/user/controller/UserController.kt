package com.gachi.gb.user.controller

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
  private val userService: UserService
) {
  @GetMapping("/user")
  fun getUser(userId: String): User {
    return userService.getUser(userId)
  }

}