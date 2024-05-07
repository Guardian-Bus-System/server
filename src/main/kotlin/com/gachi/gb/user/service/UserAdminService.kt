package com.gachi.gb.user.service

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminUpdateDto

interface UserAdminService {
  fun getUsers(): List<User>
  fun updateUser(dto: UserAdminUpdateDto): String
}