package com.gachi.gb.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role (
  @Id
  @Column(name = "name")
  var name: String,

  @Column(name = "description")
  var description: String
) {
}