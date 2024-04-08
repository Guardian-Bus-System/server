package com.gachi.gb.user.service

import com.gachi.gb.user.domain.User

interface UserService {
  fun getUser(userId: String): User
}