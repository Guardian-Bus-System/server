package com.gachi.gb.bus.service.impl.city

import com.gachi.gb.bus.dto.BusCityDto
import com.gachi.gb.bus.repository.BusCityRepository
import com.gachi.gb.bus.service.BusCityService
import org.springframework.stereotype.Service

@Service
class BusCityServiceImpl(
  private val busCityRepository: BusCityRepository
): BusCityService {
  override fun getCites(): List<BusCityDto.GetList> {
    return busCityRepository.findAll().map {
      BusCityDto.GetList(
        it.id,
        it.cityName
      )
    }
  }

  override fun getCity(cityId: Int): BusCityDto.Get {
    val busCity =  busCityRepository.findById(cityId).orElseThrow {
      IllegalArgumentException("해당 지역이 존재하지 않습니다.")
    }

    return BusCityDto.Get(
      busCity.id,
      busCity.cityName,
      busCity.towns
    )
  }
}