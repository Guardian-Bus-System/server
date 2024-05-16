package com.gachi.gb.bus.domain

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime
import java.util.*

@Entity
class Bus (
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID?,

  //호차 번호
  @Column(nullable = false)
  var busNumber: Int,

  //호차 이름
  @Column(nullable = false)
  var busName: String,

  //중간 지점
  @OneToMany
  var lines: List<BusDetails>,

  //종착지
  @OneToMany
  var endLines: List<BusDetails>,

  //좌석 수
  @Column(nullable = false)
  var maxTable: Int = 45,

  @Column(nullable = false)
  var createdAt: LocalDateTime,

  @Column(nullable = true)
  var updateAt: LocalDateTime
) {
}