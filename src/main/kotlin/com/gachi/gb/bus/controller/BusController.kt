package com.gachi.gb.bus.controller

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.service.BusService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/buses")
class BusController (
  private val busService: BusService
) {
  //버스 일괄 조회
  @GetMapping("/{busId}")
  fun getBus(@PathVariable busId: Int): CommonResponse<Bus> {
    return ok(busService.getBus(busId))
  }

  //버스 목록 조회
  @GetMapping("/")
  fun getBuses(): CommonResponse<List<Bus>> {
    return ok(busService.getBuses())
  }
}