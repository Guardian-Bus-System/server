package com.gachi.gb.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "classInfo")
class ClassInfo (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int,

  @Column(nullable = false)
  var grade: Int,

  @Column(nullable = false)
  var classNumber: Int,

  @Column(nullable = false)
  var number: Int
) {
}