package com.gachi.gb.rez.domain

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.user.domain.User
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "bus_reservation")
class BusReservation (
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID?,

  //호차 정보
  @ManyToOne
  @JoinColumn(name = "bus_id")
  var bus: Bus,

  //최종 목적지
  @Column(nullable = false)
  var endCity: String,

  //탑승 여부
  @Column(nullable = true, columnDefinition = "BOOLEAN DEFAULT TRUE")
  var onCk: Boolean = false,

  @Column(nullable = false, updatable = false)
  var createAt: LocalDateTime,

  @Column(nullable = true, updatable = true)
  var updateAt: LocalDateTime?,

  @OneToOne(fetch = FetchType.LAZY)
  var user: User,
) {
}