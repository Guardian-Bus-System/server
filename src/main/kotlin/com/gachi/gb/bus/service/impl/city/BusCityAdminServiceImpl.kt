package com.gachi.gb.bus.service.impl.city

import com.gachi.gb.bus.domain.BusCity
import com.gachi.gb.bus.dto.BusCityAdminDto
import com.gachi.gb.bus.repository.BusCityRepository
import com.gachi.gb.bus.service.BusCityAdminService
import org.springframework.stereotype.Service

@Service
class BusCityAdminServiceImpl(
  private val busCityRepository: BusCityRepository
): BusCityAdminService {
  override fun getBusCities(): List<BusCityAdminDto.GetList> {
    return busCityRepository.findAll().map {
      BusCityAdminDto.GetList(
        it.id,
        it.cityName
      )
    }
  }

  override fun getCity(cityId: Int): BusCityAdminDto.Get {
    val busCity = busCityRepository.findById(cityId).orElseThrow {
      IllegalArgumentException("해당 도시가 존재하지 않습니다.")
    }

    return BusCityAdminDto.Get(
      busCity.id,
      busCity.cityName,
      busCity.towns
    )
  }

  override fun addCity(dto: BusCityAdminDto.Add): String {
    if(busCityRepository.existsByCityName(dto.name)) {
      throw IllegalArgumentException("이미 해당 이름의 도시가 존재합니다.")
    }

    val busCity = BusCity(
      null,
      dto.name,
      null
    )

    busCityRepository.save(busCity)

    return "도시 추가가 완료되었습니다."
  }

  override fun updateCity(cityId: Int, dto: BusCityAdminDto.Update): String {
    val busCity = busCityRepository.findById(cityId).orElseThrow {
      IllegalArgumentException("해당 도시가 존재하지 않습니다.")
    }

    busCity.cityName = dto.name

    busCityRepository.save(busCity)

    return "도시 수정이 완료되었습니다."
  }

  override fun deleteCity(cityId: Int): String {
    val busCity = busCityRepository.findById(cityId).orElseThrow {
      IllegalArgumentException("해당 도시가 존재하지 않습니다.")
    }

    busCityRepository.delete(busCity)

    return "도시 삭제가 완료되었습니다."
  }
}