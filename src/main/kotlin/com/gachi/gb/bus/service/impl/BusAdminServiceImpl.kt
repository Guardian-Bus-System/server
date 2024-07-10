package com.gachi.gb.bus.service.impl

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.dto.BusAdminDto
import com.gachi.gb.bus.repository.BusCityRepository
import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.bus.service.BusAdminService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BusAdminServiceImpl (
  private val busRepository: BusRepository,
  private val busCityRepository: BusCityRepository
): BusAdminService {
  override fun getBus(busId: Int): Bus {
    return busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 호차의 버스가 존재하지 않습니다.")
    }
  }

  override fun getBuses(): List<Bus> {
    return busRepository.findAll()
  }

  override fun addBus(dto: BusAdminDto.Add): String {
    if(busRepository.existsByBusNumber(dto.busNumber)) {
      throw IllegalArgumentException("이미 해당 버스가 존재합니다.")
    }

    val busCity = busCityRepository.findById(dto.titleCity).orElseThrow {
      IllegalArgumentException("지역이 존재하지 않습니다.")
    }

    val matchingTowns = busCity.towns?.filter {
      it.id in dto.towns
    } ?: emptyList()



    val newBus = Bus(
      null,
      dto.busNumber,
      dto.busName,
      busCity.cityName,
      matchingTowns.toMutableList(),
      dto.maxTable,
      LocalDateTime.now(),
      LocalDateTime.now(),
      null,
    )

    busRepository.save(newBus)

    return "버스 추가가 완료 되었습니다."
  }

  override fun updateBus(
    busId: Int,
    dto: BusAdminDto.Update
  ): String {
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 호차의 버스가 존재하지 않습니다.")
    }

    val busCity = busCityRepository.findById(dto.titleCity).orElseThrow {
      IllegalArgumentException("지역이 존재하지 않습니다.")
    }

    // busCity의 towns와 dto의 towns를 비교하여 일치하는 값을 추출
    val matchingTowns = busCity.towns?.filter { it.id in dto.towns } ?: emptyList()

    if (matchingTowns.isEmpty()) {
      throw IllegalArgumentException("일치하는 마을이 없습니다.")
    }

    // 기존 버스 엔티티 업데이트
    bus.busNumber = dto.busNumber
    bus.busName = dto.busName
    bus.titleCityName = busCity.cityName
    bus.towns = matchingTowns.toMutableList()
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