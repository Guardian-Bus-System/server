package com.gachi.gb.bus.service

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.dto.BusAdminDto

interface BusAdminService {

  fun getBus(busId: Int): Bus

  fun getBuses(): List<Bus>

  fun addBus(dto: BusAdminDto.Add): String

  fun updateBus(
    busId: Int,
    dto: BusAdminDto.Update
  ): String

  fun deleteBus(busId: Int): String
}