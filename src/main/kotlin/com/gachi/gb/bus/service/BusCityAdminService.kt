package com.gachi.gb.bus.service

import com.gachi.gb.bus.dto.BusCityAdminDto

interface BusCityAdminService {
  fun getBusCities(): List<BusCityAdminDto.GetList>

  fun getCity(cityId: Int): BusCityAdminDto.Get

  fun addCity(dto: BusCityAdminDto.Add): String

  fun updateCity(
    cityId: Int,
    dto: BusCityAdminDto.Update
  ): String

  fun deleteCity(cityId: Int): String


}

// 승보, 민정, 혜승, 찬빈