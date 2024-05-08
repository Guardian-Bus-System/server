package com.gachi.gb.bus.service.impl

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.dto.BusAddAdminDto
import com.gachi.gb.bus.dto.BusUpdateAdminDto
import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.bus.service.BusAdminService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Date

@Service
class BusAdminServiceImpl (
  private val busRepository: BusRepository
): BusAdminService {
  override fun getBus(busId: Int): Bus {
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 호차의 버스가 존재하지 않습니다.")
    }

    return bus
  }

  override fun getBuses(): List<Bus> {
    return busRepository.findAll()
  }

  override fun addBus(dto: BusAddAdminDto): String {
    if(busRepository.existsByBusNumber(dto.busNumber)) {
      throw IllegalArgumentException("이미 해당 버스가 존재합니다.")
    }

    val newBus = Bus(
      null,
      dto.busNumber,
      dto.line,
      dto.endLine,
      dto.maxTable,
      LocalDateTime.now(),
      LocalDateTime.now()
    )

    busRepository.save(newBus)

    return "버스 추가가 완료 되었습니다."
  }

  override fun updateBus(
    busId: Int,
    dto: BusUpdateAdminDto
  ): String {
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 호차의 버스가 존재하지 않습니다.")
    }

    bus.busNumber = dto.busNumber
    bus.line = dto.line
    bus.endLine = dto.endLine
    bus.maxTable = dto.maxTable
    bus.updateAt = LocalDateTime.now()

    busRepository.save(bus)

    return "버스 수정이 완료 되었습니다."
  }

  override fun deleteBus(busId: Int): String {
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 호차의 버스가 존재하지 않습니다.")
    }

    busRepository.delete(bus)

    return "버스 삭제가 완료 되었습니다."
  }
}