package com.gachi.gb.user.repository

import com.gachi.gb.user.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, String> {
}