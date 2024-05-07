package com.gachi.gb.bus.repository

import com.gachi.gb.bus.domain.Bus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BusRepository: JpaRepository<Bus, Int> {
  fun findByBusNumber(busNumber: Int): Optional<Bus>
}