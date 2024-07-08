package com.gachi.gb.bus.service

import com.gachi.gb.bus.dto.BusTownAdminDto

interface BusTownAdminService {
  fun getTown(townId: Int): BusTownAdminDto.Get
  fun addTown(cityId: Int, dto: BusTownAdminDto.Add): String
  fun updateTown(cityId: Int, townId: Int, dto: BusTownAdminDto.Update): String
  fun deleteTown(cityId: Int, townId: Int): String

}