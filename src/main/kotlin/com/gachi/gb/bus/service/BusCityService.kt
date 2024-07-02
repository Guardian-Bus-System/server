package com.gachi.gb.bus.service

import com.gachi.gb.bus.domain.BusCity
import com.gachi.gb.bus.dto.BusCityDto

interface BusCityService {
  fun getCites(): List<BusCityDto.GetList>

  fun getCity(cityId: Int): BusCityDto.Get

}