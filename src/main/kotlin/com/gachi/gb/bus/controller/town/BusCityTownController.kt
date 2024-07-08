package com.gachi.gb.bus.controller.town

import com.gachi.gb.bus.dto.BusTownDto
import com.gachi.gb.bus.service.BusCityTownService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bus/city/towns")
class BusCityTownController (
  private val busCityTownService: BusCityTownService
) {
  @GetMapping("/{town}")
  fun getTown(
    @PathVariable("town") townId: Int
  ): CommonResponse<BusTownDto.Get> {
    return ok(busCityTownService.getTown(townId))
  }
}