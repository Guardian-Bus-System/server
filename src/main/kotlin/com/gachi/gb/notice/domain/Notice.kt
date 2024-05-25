package com.gachi.gb.notice.domain

import com.gachi.gb.user.domain.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Notice (
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID?,

  var title: String,
  var content: String,
  var tag: String,
  var createAt: LocalDateTime,
  var updateAt: LocalDateTime?,

  @OneToOne
  var uploadUser: User
) {
}