package com.gachi.gb.bus.controller

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.dto.BusAdminDto
import com.gachi.gb.bus.service.BusAdminService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자 버스", description = "관리자용 버스 관리 API")
@RestController
@RequestMapping("/admin/buses")
class BusAdminController (
  private val busAdminService: BusAdminService
) {
  @Operation(summary = "버스 목록 조회", description = "등록된 버스 목록 조회 API")
  @GetMapping
  fun getBuses(): CommonResponse<List<Bus>> {
    return ok(busAdminService.getBuses())
  }

  @Operation(summary = "버스 상세 조회", description = "버스 id를 통한 개별 조회 API")
  @GetMapping("/{bus}")
  fun getBus(@PathVariable("bus") busId: Int): CommonResponse<Bus> {
    return ok(busAdminService.getBus(busId))
  }

  @Operation(summary = "버스 추가", description = "버스 추가 API")
  @GetMapping("/bus/add")
  fun addBus(@RequestBody dto: BusAdminDto.Add): CommonResponse<String> {
    return ok(busAdminService.addBus(dto))
  }

  @Operation(summary = "버스 수정 API", description = "버스 정보를 수정할 수 있는 API")
  @PutMapping("/{bus}")
  fun updateBus(
    @PathVariable("bus") busId: Int,
    @RequestBody dto: BusAdminDto.Update
  ): CommonResponse<String> {
    return ok(busAdminService.updateBus(busId, dto))
  }

  @Operation(summary = "버스 일괄 삭제 API", description = "버스를 개별 삭제 API")
  @DeleteMapping("/{bus}")
  fun deleteBus(@PathVariable("bus") busId: Int): CommonResponse<String> {
    return ok(busAdminService.deleteBus(busId))
  }
}