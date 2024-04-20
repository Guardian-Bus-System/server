package com.gachi.gb.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.GenericGenerator
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

  @OneToOne
  var classInfo: ClassInfo,

  @ManyToMany
  var roles: MutableList<Role>
) {
  fun generateAuthorities(): List<GrantedAuthority> {
    return roles.map {role ->
      SimpleGrantedAuthority(role.name)
    }
  }
}