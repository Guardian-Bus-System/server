package com.gachi.gb.notice.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
class NoticeViews(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "notice_view_id")
  @JsonIgnore
  var notice: Notice,

  @Column(nullable = false)
  var views: UUID?
) {
}