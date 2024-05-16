package com.gachi.gb.bus.service.impl

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.bus.service.BusService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BusServiceImpl(
  private val busRepository: BusRepository
):BusService {
  override fun getBus(busId: UUID): Bus {
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 버스가 존재하지 않습니다.")
    }
    return bus
  }

  override fun getBuses(): MutableList<Bus> {
    return busRepository.findAll()
  }
}