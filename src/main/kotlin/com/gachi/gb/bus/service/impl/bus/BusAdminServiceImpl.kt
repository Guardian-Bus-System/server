package com.gachi.gb.bus.service.impl.bus

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
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 호차의 버스가 존재하지 않습니다.")
    }

    return bus
  }

  override fun getBuses(): List<Bus> {
    return busRepository.findAll()
  }

  override fun addBus(dto: BusAdminDto.Add): String {
    if(busRepository.existsByBusNumber(dto.busNumber)) {
      throw IllegalArgumentException("이미 해당 버스가 존재합니다.")
    }

    val busMiddleCity =  busCityRepository.findById(dto.middleCityId).orElseThrow {
      IllegalArgumentException("해당 지역의 중간 지점이 존재하지 않습니다.")
    }

    val busEndCity =  busCityRepository.findById(dto.endCityId).orElseThrow {
      IllegalArgumentException("해당 지역의 종착 지점이 존재하지 않습니다.")
    }

    val newBus = Bus(
      null,
      dto.busNumber,
      dto.busName,
      busMiddleCity,
      busEndCity,
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

    val busMiddleCity =  busCityRepository.findById(dto.middleCityId).orElseThrow {
      IllegalArgumentException("해당 지역의 중간 지점이 존재하지 않습니다.")
    }

    val busEndCity =  busCityRepository.findById(dto.endCityId).orElseThrow {
      IllegalArgumentException("해당 지역의 종착 지점이 존재하지 않습니다.")
    }


    bus.busNumber = dto.busNumber
    bus.busName = dto.busName
    bus.middleCity = busMiddleCity
    bus.endCity = busEndCity
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