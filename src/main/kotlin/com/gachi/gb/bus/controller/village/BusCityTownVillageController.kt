package com.gachi.gb.bus.controller.village

import com.gachi.gb.bus.dto.BusVillageDto
import com.gachi.gb.bus.service.BusVillageAdminService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/bus/cities/towns/{town}")
class BusCityTownVillageController(
  private val busVillageAdminService: BusVillageAdminService,
) {

  @PostMapping("/village")
  fun addVillage(
    @PathVariable(name = "town") townId: Int,
    @RequestBody dto: BusVillageDto.Add,
  ): CommonResponse<String> {
    return ok(busVillageAdminService.addVillage(townId, dto))
  }

  @PutMapping("/{village}")
  fun updateVillage(
    @PathVariable(name = "town") townId: Int,
    @PathVariable(name = "village") villageId: Int,
    @RequestBody dto: BusVillageDto.Update,
  ): CommonResponse<String> {
    return ok(busVillageAdminService.updateVillage(townId, villageId, dto))
  }

  @DeleteMapping("/{village}")
  fun deleteVillage(
    @PathVariable(name = "town") townId: Int,
    @PathVariable(name = "village") villageId: Int,
  ): CommonResponse<String> {
    return ok(busVillageAdminService.deleteVillage(townId, villageId))
  }

}