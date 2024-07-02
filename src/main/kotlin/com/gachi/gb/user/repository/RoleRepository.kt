package com.gachi.gb.user.repository

import com.gachi.gb.user.domain.Roles
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Roles, String> {
}