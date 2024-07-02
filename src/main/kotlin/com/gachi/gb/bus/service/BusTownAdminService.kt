package com.gachi.gb.bus.service

import com.gachi.gb.bus.dto.BusTownDto

interface BusTownAdminService {
  fun addTown(cityId: Int, dto: BusTownDto.Add): String
  fun updateTown(cityId: Int, townId: Int, dto: BusTownDto.Update): String
  fun deleteTown(cityId: Int, townId: Int): String

}