package com.gachi.gb.rez.domain

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.user.domain.User
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "bus_reservation_history")
class BusReservationHistory (
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID?,

  // 원본 예약 정보
  @ManyToOne
  @JoinColumn(name = "reservation_id", nullable = false)
  var reservation: BusReservation,

  // 변경된 호차 정보
  @ManyToOne
  @JoinColumn(name = "bus_id", nullable = false)
  var bus: Bus,

  // 변경된 최종 목적지
  @Column(nullable = false)
  var endCity: String,

  // 변경된 탑승 여부
  @Column(nullable = false)
  var onCk: Boolean,

  // 변경된 시간
  @Column(nullable = false)
  var changeAt: LocalDateTime,

  // 변경된 사용자 정보
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  var user: User
) {
}
