package com.gachi.gb.bus.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime

@Entity
class Bus (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  @Column(nullable = false)
  var busNumber: Int,

  @Column(nullable = false)
  var line: String,

  @Column(nullable = false)
  var endLine: String,

  @Column(nullable = false)
  var maxTable: Int,

  @Column(nullable = false)
  var createdAt: LocalDateTime,

  @Column(nullable = true)
  var updateAt: LocalDateTime
) {
}