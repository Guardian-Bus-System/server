package com.gachi.gb.bus.controller.city

import com.gachi.gb.bus.domain.BusCity
import com.gachi.gb.bus.dto.BusCityDto
import com.gachi.gb.bus.service.BusAdminService
import com.gachi.gb.bus.service.BusCityService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "사용자 버스 경로", description = "사용자용 버스 경로 API")
@RestController
@RequestMapping("/bus/cities")
class BusCityController (
  private val busCityService: BusCityService
) {
  @GetMapping
  fun getCites(): CommonResponse<List<BusCityDto.GetList>> {
    return ok(busCityService.getCites())
  }

  @GetMapping("/{city}")
  fun getCity(
    @PathVariable(name = "city") cityId: Int
  ): CommonResponse<BusCityDto.Get> {
    return ok(busCityService.getCity(cityId))
  }
}