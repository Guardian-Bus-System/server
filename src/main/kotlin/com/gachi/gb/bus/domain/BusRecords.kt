package com.gachi.gb.bus.domain

import com.gachi.gb.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.time.LocalDate

@Entity
class BusRecords (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int,

  @Column(nullable = false)
  val takeDate: LocalDate,

  @OneToOne
  val user: User,

  @OneToOne
  val busInfo: Bus
) {
}