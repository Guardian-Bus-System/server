package com.gachi.gb.user.domain

import com.fasterxml.jackson.annotation.JsonIgnore
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

  @Column(nullable = false)
  @JsonIgnore
  var pw: String,

  //이름 ex)정태양
  @Column(nullable = false)
  var name: String,
  //전화번호 ex) 010-7228-6054
  @Column(nullable = false)
  var phoneNumber: String,

  //학급 정보 ex) 3학년 1반
  @Column(nullable = false)
  var gradeClass: String,
  //학급 번호 ex) 14
  @Column(nullable = false)
  var number: Int,

  //탑승 여부 ex) true
  var usingCk: Boolean = false,

  //탑승 확인
  var boardingCk: Boolean? = false,

  //사용자 권한
  @ManyToMany
  var roles: MutableList<Roles>
) {
  fun getAuthorities(): List<GrantedAuthority> {
    return roles.map {role ->
      SimpleGrantedAuthority(Roles.ROLE_PREFIX + role.id)
    }
  }
}