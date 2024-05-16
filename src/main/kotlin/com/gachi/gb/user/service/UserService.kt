package com.gachi.gb.user.service

import com.gachi.gb.user.domain.User
import com.gachi.gb.user.dto.UserUpdateDto
import java.util.UUID

interface UserService {
//  fun getUser(userId: String): User

  fun getUserByLoginId(userLoginId: String): User

  fun getUserById(userId: UUID): User

  fun update(userLoginId: String, dto: UserUpdateDto): String

}