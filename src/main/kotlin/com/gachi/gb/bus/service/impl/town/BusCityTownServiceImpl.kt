package com.gachi.gb.bus.service.impl.town

import com.gachi.gb.bus.dto.BusTownDto
import com.gachi.gb.bus.repository.BusTownRepository
import com.gachi.gb.bus.service.BusCityTownService
import org.springframework.stereotype.Service

@Service
class BusCityTownServiceImpl(
  private val busCityTownRepository: BusTownRepository
): BusCityTownService {
  override fun getTown(townId: Int): BusTownDto.Get {
    val busCityTown = busCityTownRepository.findById(townId).orElseThrow {
      IllegalArgumentException("마을을 조회할 수 없습니다.")
    }

    return BusTownDto.Get(
      busCityTown.id,
      busCityTown.townName,
      busCityTown.busVillages!!
    )
  }
}