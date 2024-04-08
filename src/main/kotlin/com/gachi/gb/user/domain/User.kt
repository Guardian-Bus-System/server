package com.gachi.gb.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
class User (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  var id: Int,

  var loginId: String,
  var pw: String,

  var name: String,

  @OneToOne
  var classInfo: ClassInfo,

  @OneToMany
  var role: MutableList<Role>
) {

}