package com.gachi.gb.bus.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gachi.gb.rez.domain.BusReservation
import jakarta.persistence.*
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.time.LocalDateTime

@Entity
@Cacheable
class Bus (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  //호차 번호
  @Column(nullable = false)
  var busNumber: Int,

  //호차 이름
  @Column(nullable = false)
  var busName: String,

  //지역
  @Column(nullable = false)
  var titleCityName: String,

  @ManyToMany
  var towns: MutableList<BusTown>?,

  //좌석 수
  @Column(nullable = false)
  var maxTable: Int = 45,

  @Column(nullable = false)
  var createdAt: LocalDateTime,

  @Column(nullable = true)
  var updateAt: LocalDateTime,

  @OneToMany(mappedBy = "bus")
  @JsonIgnore
  var busReservations: MutableList<BusReservation>?
) {
}