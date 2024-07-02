package com.gachi.gb.bus.service

import com.gachi.gb.bus.dto.BusVillageDto

interface BusVillageAdminService {
  fun addVillage(townId: Int, dto: BusVillageDto.Add): String
  fun updateVillage(townId: Int, villageId: Int, dto: BusVillageDto.Update): String
  fun deleteVillage(townId: Int, villageId: Int): String

}