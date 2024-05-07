package com.gachi.gb.auth.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.beans.ConstructorProperties
import java.util.UUID

class SimpleUserPrincipal @ConstructorProperties("id", "authorities") constructor(
  val id: String,
  private val authorities: Collection<GrantedAuthority>
): UserDetails {
  override fun getAuthorities(): Collection<GrantedAuthority> {
    return authorities
  }

  override fun getPassword(): String {
    return ""
  }

  override fun getUsername(): String {
    return id
  }

  override fun isAccountNonExpired(): Boolean {
    return true
  }

  override fun isAccountNonLocked(): Boolean {
    return true
  }

  override fun isCredentialsNonExpired(): Boolean {
    return true
  }

  override fun isEnabled(): Boolean {
    return true
  }
}