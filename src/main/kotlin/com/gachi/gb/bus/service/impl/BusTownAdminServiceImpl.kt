package com.gachi.gb.bus.service.impl

import com.gachi.gb.bus.domain.BusTown
import com.gachi.gb.bus.dto.BusTownDto
import com.gachi.gb.bus.repository.BusCityRepository
import com.gachi.gb.bus.repository.BusTownRepository
import com.gachi.gb.bus.service.BusTownAdminService
import org.springframework.stereotype.Service

@Service
class BusTownAdminServiceImpl(
  private val busCityRepository: BusCityRepository,
  private val busTownRepository: BusTownRepository
): BusTownAdminService {
  override fun addTown(cityId: Int, dto: BusTownDto.Add): String {
    val busCity = busCityRepository.findById(cityId).orElseThrow { 
      IllegalArgumentException("해당 마을의 상위 도시가 존재하지 않습니다")
    }

    val town = BusTown(
      null,
      busCity,
      dto.name,
      null
    )

    busTownRepository.save(town)

    return "마을 추가가 완료되었습니다."
  }

  override fun updateTown(cityId: Int, townId: Int, dto: BusTownDto.Update): String {
    val busCity = busCityRepository.findById(cityId).orElseThrow {
      IllegalArgumentException("해당 마을의 상위 도시가 존재하지 않습니다")
    }

    val town = busTownRepository.findById(townId).orElseThrow {
      IllegalArgumentException("해당 마을이 존재하지 않습니다.")
    }

    if(busCity != town.busCity) {
      throw IllegalArgumentException("해당 마을과 도시의 관계가 잘못되었습니다.")
    }

    town.townName = dto.name

    busTownRepository.save(town)

    return "마을 수정이 완료되었습니다."
  }

  override fun deleteTown(cityId: Int, townId: Int): String {
    val busCity = busCityRepository.findById(cityId).orElseThrow {
      IllegalArgumentException("해당 마을의 상위 도시가 존재하지 않습니다")
    }

    val town = busTownRepository.findById(townId).orElseThrow {
      IllegalArgumentException("해당 마을이 존재하지 않습니다.")
    }

    if(busCity != town.busCity) {
      throw IllegalArgumentException("해당 마을과 도시의 관계가 잘못되었습니다.")
    }

    busTownRepository.delete(town)

    return "마을 삭제가 완료되었습니다."
  }
}