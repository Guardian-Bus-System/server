package com.gachi.gb.bus.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.ColumnDefault

@Entity
class Bus (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int,

  @Column(nullable = false)
  val busNumber: Int,

  @Column(nullable = false)
  val line: String,

  @Column(nullable = false)
  val endLine: String,

  @Column(nullable = false)
  val maxTable: Int
) {
}