package com.gachi.gb.notice.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gachi.gb.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Notice (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  @Column(length = 50000, nullable = false)
  var content: String,

  var createAt: LocalDateTime,
  var updateAt: LocalDateTime?,

  @OneToMany(mappedBy = "notice")
  @JsonIgnore
  var views: MutableList<NoticeViews>?
) {
}