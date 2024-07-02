package com.gachi.gb.bus.controller.town

import com.gachi.gb.bus.dto.BusTownDto
import com.gachi.gb.bus.service.BusTownAdminService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/bus/{city}/towns")
class BusCityTownAdminController(
  private val busTownAdminService: BusTownAdminService
) {
  @PostMapping("/town")
  fun addTown(
    @PathVariable(name = "city") cityId: Int,
    @RequestBody dto: BusTownDto.Add
  ): CommonResponse<String> {
    return ok(busTownAdminService.addTown(cityId, dto))
  }

  @PutMapping("/{town}")
  fun updateTown(
    @PathVariable(name = "city") cityId: Int,
    @PathVariable(name = "town") townId: Int,
    @RequestBody dto: BusTownDto.Update
  ): CommonResponse<String> {
    return ok(busTownAdminService.updateTown(cityId, townId, dto))
  }

  @DeleteMapping("/{town}")
  fun deleteTown(
    @PathVariable(name = "city") cityId: Int,
    @PathVariable(name = "town") townId: Int
  ): CommonResponse<String> {
    return ok(busTownAdminService.deleteTown(cityId, townId))
  }
}