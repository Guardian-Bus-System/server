package com.gachi.gb.bus.service

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.dto.BusAddAdminDto
import com.gachi.gb.bus.dto.BusUpdateAdminDto
import org.springframework.data.jpa.repository.JpaRepository

interface BusAdminService {

  fun getBus(busId: Int): Bus

  fun getBuses(): List<Bus>

  fun addBus(dto: BusAddAdminDto): String

  fun updateBus(
    busId: Int,
    dto: BusUpdateAdminDto
  ): String

  fun deleteBus(busId: Int): String
}