package com.gachi.gb.rez.repository

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.rez.domain.BusReservation
import com.gachi.gb.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface BusReservationRepository: JpaRepository<BusReservation, UUID> {
  fun findByUser(user: User): Optional<BusReservation>

  fun findAllByBus(bus: Bus): List<BusReservation>

  fun existsByUser(user: User): Boolean

}