package com.gachi.gb.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Role (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  var id: String,

  @Column(name = "name", nullable = false)
  var name: String,

  @Column(name = "name", nullable = false)
  var description: String
) {
}