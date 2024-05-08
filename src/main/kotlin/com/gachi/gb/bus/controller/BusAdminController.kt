package com.gachi.gb.bus.controller

import com.gachi.gb.bus.domain.Bus
import com.gachi.gb.bus.dto.BusAddAdminDto
import com.gachi.gb.bus.dto.BusUpdateAdminDto
import com.gachi.gb.bus.service.BusAdminService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/buses")
class BusAdminController (
  private val busAdminService: BusAdminService
) {
  @GetMapping("/{busId}")
  fun getBus(@PathVariable busId: Int): CommonResponse<Bus> {
    return ok(busAdminService.getBus(busId))
  }

  @GetMapping("/")
  fun getBuses(): CommonResponse<List<Bus>> {
    return ok(busAdminService.getBuses())
  }

  @GetMapping("/bus/add")
  fun addBus(@RequestBody dto: BusAddAdminDto): CommonResponse<String> {
    return ok(busAdminService.addBus(dto))
  }

  @PutMapping("/{busId}")
  fun updateBus(
    @PathVariable busId: Int,
    @RequestBody dto: BusUpdateAdminDto
  ): CommonResponse<String> {
    return ok(busAdminService.updateBus(busId, dto))
  }

  @DeleteMapping("/{busId}")
  fun deleteBus(@PathVariable busId: Int): CommonResponse<String> {
    return ok(busAdminService.deleteBus(busId))
  }
}