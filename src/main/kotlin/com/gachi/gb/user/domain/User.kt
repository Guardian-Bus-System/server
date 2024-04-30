package com.gachi.gb.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.UUID

@Entity
class User (
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID?,

  @Column(nullable = false, unique = true)
  var loginId: String,
  var pw: String,

  var name: String,

  //ClassInfo
  var grade: Int,
  var classNumber: Int,
  var number: Int,

  @ManyToMany
  var roles: MutableList<Role>
) {
  fun getAuthorities(): List<GrantedAuthority> {
    return roles.map {role ->
      SimpleGrantedAuthority(role.name)
    }
  }
}