package com.gachi.gb.rule.domain

import com.gachi.gb.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Rule (

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false, unique = true)
  var id: Long?,

  @Column(nullable = false, updatable = true, unique = true)
  var number: Long,

  @Column(nullable = false, updatable = true)
  var content: String,

  @Column(nullable = false, updatable = false)
  var createAt: LocalDateTime,

  @Column(nullable = true, updatable = true)
  var updateAt: LocalDateTime?,

  @OneToOne
  var user: User
) {

}