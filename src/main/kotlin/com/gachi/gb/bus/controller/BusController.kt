package com.gachi.gb.bus.controller

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.service.BusService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "사용자 버스", description = "사용자용 버스 조회 및 탑승 추가 API")
@RestController
@RequestMapping("/buses")
class BusController (
  private val busService: BusService
) {
  //버스 일괄 조회
  @Operation(summary = "버스 상세 조회", description = "버스 id를 통한 개별 조회 API")
  @GetMapping("/{bus}")
  fun getBus(@PathVariable("bus") busId: Int): CommonResponse<Bus> {
    return ok(busService.getBus(busId))
  }

  //버스 목록 조회
  @Operation(summary = "버스 목록 조회", description = "버스의 목록을 조회할 수 있음")
  @GetMapping
  fun getBuses(): CommonResponse<List<Bus>> {
    return ok(busService.getBuses())
  }
}