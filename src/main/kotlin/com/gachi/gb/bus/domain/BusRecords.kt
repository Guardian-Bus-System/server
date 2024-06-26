package com.gachi.gb.bus.domain

import com.gachi.gb.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.time.LocalDate

//버스 기록
@Entity
class BusRecords (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int,

  @Column(nullable = false)
  var takeDate: LocalDate,

  @OneToOne
  var user: User,

  @OneToOne
  var busInfo: Bus
) {
}