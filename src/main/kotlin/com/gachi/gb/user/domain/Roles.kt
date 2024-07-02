package com.gachi.gb.user.domain

import jakarta.persistence.*

@Entity
class Roles (
  @Id
  var id: String,
  var title: String
) {
  companion object {
    const val ROLE_PREFIX = "ROLE_"
  }
}