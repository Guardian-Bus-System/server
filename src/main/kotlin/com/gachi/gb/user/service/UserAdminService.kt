package com.gachi.gb.user.service

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserAdminAddDto
import com.gachi.gb.user.dto.UserAdminUpdateDto
import java.util.UUID

interface UserAdminService {
  fun getUsers(): List<User>
  fun addUser(dto: UserAdminAddDto): String
  fun updateUser(dto: UserAdminUpdateDto): String
  fun deleteUser(userId: UUID): String

}