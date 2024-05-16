package com.gachi.gb.bus.repository

import com.gachi.gb.bus.domain.Bus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface BusRepository: JpaRepository<Bus, Int> {

  fun findById(busId: UUID): Optional<Bus>
  fun findByBusNumber(busNumber: Int?): Optional<Bus>
  fun existsByBusNumber(busNumber: Int): Boolean
}